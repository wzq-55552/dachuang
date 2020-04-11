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
@Table(name = "m_report")
@ApiModel(description = "中期报告表m_report",value = "中期报告表m_report")
public class MReport implements Serializable {

    /**
     * 自增id
     */
    @Id
    @Column(name = "report_id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "自增id",required = true)
    private Long reportId;

    /**
     * 负责人
     */
    @Column(name = "user_id")
    @ApiModelProperty(value = "负责人id",required = true)
    private String userId;

    /**
     * 中期文件解释，类似encoder
     */
    @Column(name = "content")
    @ApiModelProperty(value = "中期文件解释，类似encoder",required = false)
    private String content;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    @ApiModelProperty(value = "项目id",required = true)
    private Long projectId;

    /**
     * 导师评语
     */
    @Column(name = "t_comment")
    @ApiModelProperty(value = "导师评语",required = false)
    private String tComment;

    /**
     * 导师认可，未审核0，不通过1，通过2，退回修改3
     */
    @Column(name = "t_approval")
    @ApiModelProperty(value = "导师认可，未审核0，不通过1，通过2，退回修改3",required = true)
    private Integer tApproval;

    /**
     * 学院评语
     */
    @Column(name = "c_comment")
    @ApiModelProperty(value = "学院评语",required = false)
    private String cComment;

    /**
     * 学院认可，未审核0，不通过1，通过2，退回学生3，退回导师4
     */
    @Column(name = "c_approval")
    @ApiModelProperty(value = "学院认可，未审核0，不通过1，通过2，退回学生3，退回导师4",required = true)
    private Integer cApproval;

    /**
     * 评审专家id
     */
    @Column(name = "expert")
    @ApiModelProperty(value = "评审专家id",required = false)
    private String expert;

    /**
     * 大创管理评语
     */
    @Column(name = "s_comment")
    @ApiModelProperty(value = "大创管理评语",required = false)
    private String sComment;

    /**
     * 大创管理认可，未审核0，通过2，退回修改3
     */
    @Column(name = "s_approval")
    @ApiModelProperty(value = "大创管理认可，未审核0，通过2，退回修改3",required = true)
    private Integer sApproval;

    /**
     * 评审专家评语语
     */
    @Column(name = "e_comment")
    @ApiModelProperty(value = "评审专家评语",required = false)
    private String eComment;

    /**
     * 评审专家认可，未审核0，不通过1，通过2，暂缓通过3
     */
    @Column(name = "e_approval")
    @ApiModelProperty(value = "评审专家认可，未审核0，不通过1，通过2，暂缓通过3",required = true)
    private Integer eApproval;

    private static final long serialVersionUID = -2008191220020982848L;
}