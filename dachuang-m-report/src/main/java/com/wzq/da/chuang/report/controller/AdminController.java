package com.wzq.da.chuang.report.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.wzq.da.chuang.commons.dto.ResponseResult;
import com.wzq.da.chuang.model.dto.report.AdminApprovalParam;
import com.wzq.da.chuang.model.dto.report.ExcelPojo;
import com.wzq.da.chuang.model.dto.report.ExpertSelectParam;
import com.wzq.da.chuang.model.dto.report.ReportSelectDto;
import com.wzq.da.chuang.model.pojos.report.MFile;
import com.wzq.da.chuang.model.pojos.report.MReport;
import com.wzq.da.chuang.model.pojos.report.Project;
import com.wzq.da.chuang.model.pojos.user.College;
import com.wzq.da.chuang.model.pojos.user.UserInformation;
import com.wzq.da.chuang.report.service.CollegeService;
import com.wzq.da.chuang.report.service.MFileService;
import com.wzq.da.chuang.report.service.MReportService;
import com.wzq.da.chuang.report.service.ProjectService;
import com.wzq.da.chuang.report.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/report/admin")
@Api(tags = "大创管理者操作内容controller")
public class AdminController {

    @Resource
    private MReportService mReportService;

    @Resource
    private UserService userService;

    @Resource
    private ProjectService projectService;

    @Resource
    private MFileService mFileService;

    @Resource
    private CollegeService collegeService;

    /**
     * 大创管理者查询所有专家
     * @return
     */
    @GetMapping("/select/expert/all")
    @ApiOperation(value = "大创管理者查询所有专家")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<List<UserInformation>> selectExpert(){
        List<UserInformation> userInformations = userService.selectExpert();
        return new ResponseResult<List<UserInformation>>(ResponseResult.CodeStatus.OK,"获取成功",userInformations);
    }

    /**
     * 大创管理者获取所有中期报告情况
     * @return
     */
    @GetMapping("/select/all")
    @ApiOperation(value = "大创管理者获取所有中期报告情况,如果专家id为空就是未指派，设置分类")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<List<ReportSelectDto>> selectAll(@RequestBody Map<String,String> userIdMap){
        if (!StringUtils.isEmpty(userIdMap.get("userId"))){
            List<ReportSelectDto> reportSelectDtos = new ArrayList<>();
            List<Project> projects = projectService.selectSelfProject(userIdMap.get("userId"));
            if (projects!= null && projects.size()>0){
                projects.forEach(project -> {
                    //project
                    ReportSelectDto reportSelectDto = new ReportSelectDto();
                    reportSelectDto.setProjectName(project.getProjectName());
                    UserInformation teacher = userService.selectByPrimaryKey(project.getOneId());
                    reportSelectDto.setTeacherName(teacher.getUserName());
                    UserInformation user = userService.selectByPrimaryKey(project.getUserId());
                    reportSelectDto.setUserName(user.getUserName());
                    reportSelectDto.setSubmit(project.getMReport());
                    College college = collegeService.selectByPrimaryKey(project.getCollegeId());
                    reportSelectDto.setCollegeName(college.getCollegeName());

                    //报告提交
                    if (project.getMReport().equals(1)){
                        MReport mReport = mReportService.selectByProjectId(project.getProjectId());
                        reportSelectDto.setMReport(mReport);
                        List<MFile> mFiles = mFileService.selectByReportId(mReport.getReportId());
                        if (mFiles!=null&&mFiles.size()>0){
                            reportSelectDto.setMFiles(mFiles);
                        }
                        if (!StringUtils.isEmpty(mReport.getExpert())){
                            UserInformation expert = userService.selectByPrimaryKey(mReport.getExpert());
                            reportSelectDto.setExpertName(expert.getUserName());
                        }
                    }

                    reportSelectDtos.add(reportSelectDto);
                });
            }
            return new ResponseResult<List<ReportSelectDto>>(ResponseResult.CodeStatus.OK,"获取数据成功",reportSelectDtos);
        }

        return new ResponseResult<List<ReportSelectDto>>(ResponseResult.CodeStatus.FAIL,"参数为空",null);
    }

    /**
     * 大创管理者给中期报告选派专家,可批量
     * @param expertSelectParam
     * @return
     */
    @PostMapping("/set/expert")
    @ApiOperation(value = "大创管理者给中期报告选派专家,可批量，根据中期报告的专家id为空就标记为选派")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Void> setExpert(@RequestBody ExpertSelectParam expertSelectParam){
        if (expertSelectParam != null && !StringUtils.isEmpty(expertSelectParam.getExpert())
        && expertSelectParam.getReportIds()!=null && expertSelectParam.getReportIds().size()>0){

            List<String> reportIds = expertSelectParam.getReportIds();
            //批量修改
            reportIds.forEach(reportId->{
                Long aLong = Long.valueOf(reportId);

                MReport mReport = new MReport();

                mReport.setReportId(aLong);
                mReport.setExpert(expertSelectParam.getExpert());

                mReportService.updateByPrimaryKeySelective(mReport);
            });
            return new ResponseResult<>(ResponseResult.CodeStatus.OK,"选派专家成功");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"参数不足");
    }

    /**
     * 大创管理者认可中期报告
     * @param adminApprovalParam
     * @return
     */
    @PostMapping("/approval")
    @ApiOperation(value = "大创管理者退回修改，意见")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Void> approval(@RequestBody AdminApprovalParam adminApprovalParam){
        if (adminApprovalParam!=null && adminApprovalParam.getReportId()!=null
        && adminApprovalParam.getApproval()!=null ){
            if (mReportService.selectByPrimaryKey(adminApprovalParam.getReportId())==null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"中期报告不存在");
            }
            MReport mReport = new MReport();
            mReport.setSApproval(adminApprovalParam.getApproval());
            mReport.setSComment(adminApprovalParam.getComment());
            mReport.setReportId(adminApprovalParam.getReportId());
            mReportService.updateByPrimaryKeySelective(mReport);
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"认可成功");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"参数不足");
    }

    /**
     * 大创管理者汇总生成报表
     * 要不要分国家级、省级、校级？？？
     * @return
     */
    //xxxx年国家级、省级大学生创新创业训练项目中期检查验收结果
    @PostMapping("/excel")
    @ApiOperation(value = "大创管理者汇总生成报表")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public void excel(HttpServletResponse response){

        List<College> colleges = collegeService.selectAll();

        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();

        //获取当前年份
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));

        // 一个学院一个分Sheet
        colleges.forEach(college -> {
            // 当前sheet,切换，从第0行开始
            writer.setSheet(college.getCollegeName());

            // 合并单元格后的标题行，使用默认标题样式
            writer.merge(9,year+"年"+college.getCollegeName()+"国家级、省级大学生创新创业训练项目中期检查验收结果");

            //自定义标题别名
            writer.addHeaderAlias("id", "项目id");
            writer.addHeaderAlias("name", "项目名");
            writer.addHeaderAlias("userName", "负责人");
            writer.addHeaderAlias("teacherName", "指导老师"); //2个老师的话拼接
            writer.addHeaderAlias("expertName", "评审专家");
            writer.addHeaderAlias("report", "是否提交");
            writer.addHeaderAlias("tApproval", "指导老师评审");
            writer.addHeaderAlias("CApproval", "二级学院评审");
            writer.addHeaderAlias("SApproval", "大创管理评审");
            writer.addHeaderAlias("EApproval", "评审专家评审");

            List<Project> projects = projectService.selectByCollegeId(college.getCollegeId());

            //对应二级学院的内容存入集合
            List<ExcelPojo> excelPojos = new ArrayList<>();

            if (projects!=null && projects.size() > 0){
                projects.forEach(project -> {
                    ExcelPojo excelPojo = new ExcelPojo();
                    excelPojo.setId(String.valueOf(project.getProjectId()));
                    excelPojo.setName(project.getProjectName());
                    UserInformation student = userService.selectByPrimaryKey(project.getUserId());
                    excelPojo.setUserName(student.getUserName());
                    UserInformation teacher1 = userService.selectByPrimaryKey(project.getOneId());
                    String s = teacher1.getUserName();
                    if (!StringUtils.isEmpty(project.getTwoId())){
                        UserInformation teacher2 = userService.selectByPrimaryKey(project.getTwoId());
                        s = s + "，" + teacher2.getUserName();
                    }
                    excelPojo.setTeacherName(s);
                    if (project.getMReport().equals(1)){
                        excelPojo.setReport("已提交");
                        MReport mReport = mReportService.selectByProjectId(project.getProjectId());
                        if (!StringUtils.isEmpty(mReport.getExpert())){
                            UserInformation expert = userService.selectByPrimaryKey(mReport.getExpert());
                            excelPojo.setExpertName(expert.getUserName());
                        }else {
                            excelPojo.setExpertName("无");
                            excelPojo.setEApproval("无");
                        }
                        //指导老师
                        if (mReport.getTApproval().equals(0)){
                            //未审核
                            excelPojo.setTApproval("未审核");
                        }else if(mReport.getTApproval().equals(1)){
                            //不通过
                            excelPojo.setTApproval("不通过");
                        }else if (mReport.getTApproval().equals(2)){
                            //通过
                            excelPojo.setTApproval("通过");
                        }else{
                            //退回修改
                            excelPojo.setTApproval("退回修改");
                        }

                        //二级学院
                        //指导老师
                        if (mReport.getCApproval().equals(0)){
                            //未审核
                            excelPojo.setCApproval("未审核");
                        }else if(mReport.getCApproval().equals(1)){
                            //不通过
                            excelPojo.setCApproval("不通过");
                        }else if(mReport.getCApproval().equals(2)){
                            //通过
                            excelPojo.setCApproval("通过");
                        }else if(mReport.getCApproval().equals(3)){
                            //退回学生
                            excelPojo.setCApproval("退回学生");
                        }else{
                            //退回导师
                            excelPojo.setCApproval("退回导师");
                        }

                        //大创管理者
                        if (mReport.getSApproval().equals(0)){
                            //未审核
                            excelPojo.setSApproval("未审核");
                        }else if(mReport.getSApproval().equals(2)){
                            //不通过
                            excelPojo.setSApproval("通过");
                        }else {
                            //退回修改
                            excelPojo.setSApproval("退回修改");
                        }

                        //专家
                        if (mReport.getEApproval().equals(0)){
                            //未审核
                            excelPojo.setEApproval("未审核");
                        }else if(mReport.getEApproval().equals(1)){
                            //不通过
                            excelPojo.setEApproval("不通过");
                        }else if (mReport.getEApproval().equals(2)){
                            //通过
                            excelPojo.setEApproval("通过");
                        }else{
                            //退回修改
                            excelPojo.setEApproval("暂缓通过");
                        }

                    }else{
                        excelPojo.setReport("未提交");
                        excelPojo.setExpertName("无");
                        excelPojo.setTApproval("无");
                        excelPojo.setCApproval("无");
                        excelPojo.setSApproval("无");
                        excelPojo.setEApproval("无");
                    }
                    excelPojos.add(excelPojo);
                });
            }

            //一次性写出内容，使用默认样式，强制输出标题
            writer.write(excelPojos, true);

        });

        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");

        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        //String name = StringUtils.toUtf8String(year+"年国家级、省级大学生创新创业训练项目中期检查验收结果");
        String name = year+"年国家级、省级大学生创新创业训练项目中期检查验收结果";

        response.setHeader("Content-Disposition","attachment;filename="+name+".xls");

        ServletOutputStream out= null;

        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭writer，释放内存
            writer.close();
        }
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

}
