package com.wzq.da.chuang.model.pojos.report;

import java.io.Serializable;
import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "project")
@ApiModel(description = "项目表project",value = "项目表project")
public class Project implements Serializable {

    /**
     * 自增id
     */
    @Id
    @Column(name = "project_id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "自增id",required = true)
    private Long projectId;

    /**
     * 负责人
     */
    @Column(name = "user_id")
    @ApiModelProperty(value = "负责人id",required = true)
    private String userId;

    /**
     * 指导老师1
     */
    @Column(name = "one_id")
    @ApiModelProperty(value = "指导老师1",required = false)
    private String oneId;

    /**
     * 指导老师2
     */
    @Column(name = "two_id")
    @ApiModelProperty(value = "指导老师2",required = false)
    private String twoId;

    /**
     * 等级，无0，校1，省2，国3，默认0
     */
    @Column(name = "grade")
    @ApiModelProperty(value = "等级，无0，校1，省2，国3，默认0",required = false)
    private Integer grade;

    /**
     * 项目名字
     */
    @Column(name = "project_name")
    @ApiModelProperty(value = "项目名字",required = true)
    private String projectName;

    /**
     * 学院ID
     */
    @Column(name = "collegeId")
    @ApiModelProperty(value = "学院ID",required = true)
    private Long collegeId;

    /**
     * 申请报告提交状态，0未提交，1提交,默认0
     */
    @Column(name = "s_report")
    @ApiModelProperty(value = "申请报告提交状态，0未提交，1提交,默认0",required = true)
    private Integer sReport;

    /**
     * 中期报告提交状态，0未提交，1提交，默认0
     */
    @Column(name = "m_report")
    @ApiModelProperty(value = "中期报告提交状态，0false未提交，1true提交，默认0",required = true)
    private Integer mReport;

    /**
     * 结题报告提交状态，0未提交，1提交，默认0
     */
    @Column(name = "f_report")
    @ApiModelProperty(value = "结题报告提交状态，0未提交，1提交，默认0",required = true)
    private Integer fReport;

    private static final long serialVersionUID = -1643384171842193695L;
}