package com.wzq.da.chuang.report.controller;

import com.wzq.da.chuang.commons.dto.ResponseResult;
import com.wzq.da.chuang.model.dto.report.ReportSelectDto;
import com.wzq.da.chuang.model.dto.report.ReportSelectParam;
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
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/report")
@Api(tags = "通用的中期报告controller")
public class ReportController {

    @Resource
    private ProjectService projectService;

    @Resource
    private MReportService mReportService;

    @Resource
    private MFileService mFileService;

    @Resource
    private UserService userService;

    /**
     * 通过项目id查看中期报告信息
     * @param projectId
     * @return
     */
    @GetMapping("/select/{projectId}")
    @ApiOperation(value = "通过项目id查看中期报告信息，学生等角色点击项目进去看中期报告评审意见")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<ReportSelectDto> selectByProjectId(@PathVariable("projectId") String projectId){
        if (!StringUtils.isEmpty(projectId)){
            Long aLong = Long.valueOf(projectId);
            MReport mReport = mReportService.selectByProjectId(aLong);
            if (mReport == null){
                return new ResponseResult<ReportSelectDto>(ResponseResult.CodeStatus.FAIL,"中期报告不存在",null);
            }
            Project project = projectService.selectByPrimaryKey(mReport.getProjectId());
            ReportSelectDto reportSelectDto = new ReportSelectDto();
            reportSelectDto.setProjectName(project.getProjectName());
            reportSelectDto.setMReport(mReport);
            List<MFile> mFiles = mFileService.selectByReportId(mReport.getReportId());
            if (mFiles != null){
                reportSelectDto.setMFiles(mFiles);
            }

            UserInformation user = userService.selectByPrimaryKey(mReport.getUserId());
            reportSelectDto.setUserName(user.getUserName());
            UserInformation teacher = userService.selectByPrimaryKey(project.getOneId());
            reportSelectDto.setTeacherName(teacher.getUserName());
            if (!StringUtils.isEmpty(mReport.getExpert())){
                UserInformation expert = userService.selectByPrimaryKey(mReport.getExpert());
                reportSelectDto.setExpertName(expert.getUserName());
            }

            return new ResponseResult<ReportSelectDto>(ResponseResult.CodeStatus.OK,"获取中期报告内容成功",reportSelectDto);
        }
        return new ResponseResult<ReportSelectDto>(ResponseResult.CodeStatus.FAIL,"参数为空",null);
    }

    @GetMapping("/select")
    @ApiOperation(value = "二级学院管理、大创管理员查询中期报告（多字段模糊搜索），学生和指导老师不用查询，直接点击项目进去看对应的报告")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<List<ReportSelectDto>> select(@RequestBody ReportSelectParam reportSelectParam){
        if (reportSelectParam != null){
//            List<MReport> MReports = new ArrayList<>();
//            if (reportSelectParam.getReportId()!=null || reportSelectParam.getProjectId() != null ||
//            !StringUtils.isEmpty(reportSelectParam.getExpert()) || !StringUtils.isEmpty(reportSelectParam.getUserId())){
//                MReport mReport = new MReport();
//                BeanUtils.copyProperties(reportSelectParam,mReport);
//                MReports = mReportService.select(mReport);
//            }
//            if (MReports!=null && MReports.size()>0){
//                if (!StringUtils.isEmpty(reportSelectParam.getProjectName())){
//                    Iterator<MReport> iterator = MReports.iterator();
//                    while (iterator.hasNext()){
//                        MReport mReport = iterator.next();
//                        Project project = projectService.selectByPrimaryKey(mReport.getProjectId());
//                        if (!project.getProjectName().equals(reportSelectParam.getProjectName())){
//                            iterator.remove();
//                        }
//                    }
//                }
//                if (!StringUtils.isEmpty(reportSelectParam.getOneId())){
//                    Iterator<MReport> iterator = MReports.iterator();
//                    while (iterator.hasNext()){
//                        MReport mReport = iterator.next();
//                        Project project = projectService.selectByPrimaryKey(mReport.getProjectId());
//                        if (!project.getOneId().equals(reportSelectParam.getOneId())){
//                            iterator.remove();
//                        }
//                    }
//                }
//            }else {
//                Project project = new Project();
//                if (!StringUtils.isEmpty(reportSelectParam.getProjectName())){
//                    project.setProjectName(reportSelectParam.getProjectName());
//                }
//                if (!StringUtils.isEmpty(reportSelectParam.getOneId())){
//                    project.setOneId(reportSelectParam.getOneId());
//                }
//                List<Project> projects = projectService.select(project);
//                if (projects!=null && projects.size()>0){
//
//                }
//            }

            List<MReport> mReports = mReportService.selectByThree(reportSelectParam);
            List<ReportSelectDto> reportSelectDtos = new ArrayList<>();

            if (mReports!=null &&  mReports.size()>0){
                mReports.forEach(mReport -> {

                    Project project = projectService.selectByPrimaryKey(mReport.getProjectId());
                    ReportSelectDto reportSelectDto = new ReportSelectDto();
                    reportSelectDto.setProjectName(project.getProjectName());
                    reportSelectDto.setMReport(mReport);
                    List<MFile> mFiles = mFileService.selectByReportId(mReport.getReportId());
                    if (mFiles != null){
                        reportSelectDto.setMFiles(mFiles);
                    }

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

            return new ResponseResult<List<ReportSelectDto>>(ResponseResult.CodeStatus.OK,"搜索成功",reportSelectDtos);

        }
        return new ResponseResult<List<ReportSelectDto>>(ResponseResult.CodeStatus.FAIL,"参数为空",null);
    }

}
