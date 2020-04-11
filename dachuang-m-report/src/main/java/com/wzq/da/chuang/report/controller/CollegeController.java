package com.wzq.da.chuang.report.controller;

import com.wzq.da.chuang.commons.dto.ResponseResult;
import com.wzq.da.chuang.model.dto.report.CollegeApprovalParam;
import com.wzq.da.chuang.model.dto.report.ReportSelectDto;
import com.wzq.da.chuang.model.dto.report.TeacherApprovalParam;
import com.wzq.da.chuang.model.pojos.report.MFile;
import com.wzq.da.chuang.model.pojos.report.MReport;
import com.wzq.da.chuang.model.pojos.report.Project;
import com.wzq.da.chuang.model.pojos.user.UserInformation;
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
        && collegeApprovalParam.getCApproval()!=null ){
            if (mReportService.selectByPrimaryKey(collegeApprovalParam.getReportId())==null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"中期报告不存在");
            }
            MReport mReport = new MReport();
            mReport.setCApproval(collegeApprovalParam.getCApproval());
            mReport.setCComment(collegeApprovalParam.getCComment());
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
    public ResponseResult<List<ReportSelectDto>> selectAll(){
        List<ReportSelectDto> reportSelectDtos = new ArrayList<>();
        List<Project> projects = projectService.selectSelfProject("3");
        if (projects!= null && projects.size()>0){
            projects.forEach(project -> {
                ReportSelectDto reportSelectDto = new ReportSelectDto();
                reportSelectDto.setProjectName(project.getProjectName());
                MReport mReport = mReportService.selectByProjectId(project.getProjectId());
                reportSelectDto.setMReport(mReport);
                List<MFile> mFiles = mFileService.selectByReportId(mReport.getReportId());
                reportSelectDto.setMFiles(mFiles);

                UserInformation user = userService.selectByPrimaryKey(mReport.getUserId());
                reportSelectDto.setUserName(user.getUserName());
                UserInformation teacher = userService.selectByPrimaryKey(project.getOneId());
                reportSelectDto.setTeacherName(teacher.getUserName());
                if (!StringUtils.isEmpty(mReport.getExpert())){
                    UserInformation expert = userService.selectByPrimaryKey(mReport.getExpert());
                    reportSelectDto.setExpertName(expert.getUserName());
                }

                reportSelectDtos.add(reportSelectDto);
            });
        }
        return new ResponseResult<List<ReportSelectDto>>(ResponseResult.CodeStatus.OK,"获取数据成功",reportSelectDtos);
    }

}
