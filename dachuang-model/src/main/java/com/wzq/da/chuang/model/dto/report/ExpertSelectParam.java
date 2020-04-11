package com.wzq.da.chuang.model.dto.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "大创管理者指派评审专家",description = "大创管理者指派评审专家")
public class ExpertSelectParam implements Serializable {

    private static final long serialVersionUID = 2302511813218823420L;

    /**
     * 评审专家id
     */
    @ApiModelProperty(value = "评审专家id,集合过来，可批量",required = true)
    private String expert;

    @ApiModelProperty(value = "报告id集合过来，可批量",required = true)
    private List<String> reportIds;
}
