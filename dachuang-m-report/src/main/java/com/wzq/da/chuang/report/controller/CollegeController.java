package com.wzq.da.chuang.report.controller;

import com.wzq.da.chuang.commons.dto.ResponseResult;
import com.wzq.da.chuang.model.dto.report.CollegeApprovalParam;
import com.wzq.da.chuang.model.dto.report.ReportSelectDto;
import com.wzq.da.chuang.model.dto.report.TeacherApprovalParam;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/report/college")
@Api(tags = "中期报告二级学院管理员操作内容controller")
public class CollegeController {

    @Resource
    private MReportService mReportService;

    @Resource
    private ProjectService projectService;

    @Resource
    private MFileService mFileService;

    @Resource
    private UserService userService;

    @Resource
    private CollegeService collegeService;

    /**
     * 二级学院管理员认可中期报告
     * @param collegeApprovalParam
     * @return
     */
    @PostMapping("/approval")
    @ApiOperation(value = "二级学院管理员认可中期报告,一般什么认可都可以有意见")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Void> approval(@RequestBody CollegeApprovalParam collegeApprovalParam){
        if (collegeApprovalParam!=null && collegeApprovalParam.getReportId()!=null
        && collegeApprovalParam.getApproval()!=null ){
            if (mReportService.selectByPrimaryKey(collegeApprovalParam.getReportId())==null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"中期报告不存在");
            }
            MReport mReport = new MReport();
            mReport.setCApproval(collegeApprovalParam.getApproval());
            mReport.setCComment(collegeApprovalParam.getComment());
            mReport.setReportId(collegeApprovalParam.getReportId());
            mReportService.updateByPrimaryKeySelective(mReport);
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"认可成功");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"参数不足");
    }

    /**
     * 二级学院管理员获取对应学院的中期报告情况
     * @return
     */
    @GetMapping("/select/all")
    @ApiOperation(value = "二级学院管理员获取对应学院的中期报告情况")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<List<ReportSelectDto>> selectAll(@RequestBody Map<String,String>userIdMap){

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

}
