package com.wzq.da.chuang.model.dto.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "二级学院，大创管理查询中期报告条件",description = "二级学院，大创管理查询中期报告条件")
public class ReportSelectParam implements Serializable {

    /**
     * 报告id
     */
    @ApiModelProperty(value = "报告id",required = false)
    private Long reportId;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人id",required = false)
    private String userId;

    /**
     * 负责人名字
     */
    @ApiModelProperty(value = "负责人名字",required = false)
    private String userName;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id",required = false)
    private Long projectId;

    /**
     * 项目名字
     */
    @ApiModelProperty(value = "项目名字",required = false)
    private String projectName;

    /**
     * 指导老师id
     */
    @ApiModelProperty(value = "指导老师id",required = false)
    private String oneId;

    /**
     * 指导老师名字
     */
    @ApiModelProperty(value = "指导老师名字",required = false)
    private String teacherName;

    /**
     * 专家id
     */
    @ApiModelProperty(value = "专家id",required = false)
    private String expert;

    /**
     * 专家名字
     */
    @ApiModelProperty(value = "专家名字",required = false)
    private String expertName;

}
