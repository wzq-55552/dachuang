package com.wzq.da.chuang.model.dto.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "二级学院管理员认可参数",description = "二级学院管理员认可参数")
public class CollegeApprovalParam implements Serializable {

    /**
     * 报告id
     */
    @ApiModelProperty(value = "报告id",required = true)
    private Long reportId;

    /**
     * 学院评语
     */
    @ApiModelProperty(value = "学院评语",required = false)
    private String cComment;

    /**
     * 学院认可，未审核0，不通过1，通过2，退回学生3，退回导师4
     */
    @ApiModelProperty(value = "学院认可，未审核0，之后4个按钮，不通过1，通过2，退回学生3，退回导师4",required = true)
    private Integer cApproval;

}
