package com.wzq.da.chuang.report.controller;

import com.wzq.da.chuang.commons.dto.ResponseResult;
import com.wzq.da.chuang.model.dto.report.ExpertApprovalParam;
import com.wzq.da.chuang.model.dto.report.TeacherApprovalParam;
import com.wzq.da.chuang.model.pojos.report.MReport;
import com.wzq.da.chuang.report.service.MReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/report/expert")
@Api(tags = "中期报告评审专家操作内容controller")
public class ExpertController {

    @Resource
    private MReportService mReportService;

    /**
     * 评审专家认可中期报告
     * @param expertApprovalParam
     * @return
     */
    @PostMapping("/approval")
    @ApiOperation(value = "评审专家认可中期报告,一般什么认可都可以有意见")
    @PreAuthorize("isAuthenticated()") // 不用权限，请求头还是得有token
    public ResponseResult<Void> approval(@RequestBody ExpertApprovalParam expertApprovalParam){
        if (expertApprovalParam!=null && expertApprovalParam.getReportId()!=null
        && expertApprovalParam.getApproval()!=null ){
            if (mReportService.selectByPrimaryKey(expertApprovalParam.getReportId())==null){
                return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"中期报告不存在");
            }
            MReport mReport = new MReport();
            mReport.setEApproval(expertApprovalParam.getApproval());
            mReport.setEComment(expertApprovalParam.getComment());
            mReport.setReportId(expertApprovalParam.getReportId());
            mReportService.updateByPrimaryKeySelective(mReport);
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"认可成功");
        }
        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"参数不足");
    }

}
