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
@ApiModel(value = "评审专家认可参数",description = "二级学院管理员认可参数")
public class ExpertApprovalParam implements Serializable {

    private static final long serialVersionUID = -5853063762547223925L;

    /**
     * 报告id
     */
    @ApiModelProperty(value = "报告id",required = true)
    private Long reportId;

    /**
     * 评审专家评语语
     */
    @ApiModelProperty(value = "评审专家评语",required = false)
    private String eComment;

    /**
     * 评审专家认可，未审核0，不通过1，通过2，暂缓通过3
     */
    @ApiModelProperty(value = "评审专家认可，未审核0，不通过1，通过2，暂缓通过3",required = true)
    private Integer eApproval;

}
