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
@ApiModel(value = "大创管理员认可参数",description = "二级学院管理员认可参数")
public class AdminApprovalParam implements Serializable {

    private static final long serialVersionUID = -5853063762547223925L;

    /**
     * 报告id
     */
    @ApiModelProperty(value = "报告id",required = true)
    private Long reportId;

    /**
     * 大创管理评语
     */
    @ApiModelProperty(value = "大创管理评语",required = false)
    private String sComment;

    /**
     * 大创管理认可，未审核0，通过2，退回修改3
     */
    @ApiModelProperty(value = "大创管理认可，未审核0，通过2，退回修改3",required = true)
    private Integer sApproval;

}
