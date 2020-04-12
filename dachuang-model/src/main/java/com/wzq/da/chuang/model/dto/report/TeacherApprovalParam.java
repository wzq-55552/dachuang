package com.wzq.da.chuang.model.dto.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "指导老师认可参数",description = "指导老师认可参数")
public class TeacherApprovalParam implements Serializable {

    /**
     * 报告id
     */
    @ApiModelProperty(value = "报告id",required = true)
    private Long reportId;

    /**
     * 导师评语
     */
    @ApiModelProperty(value = "导师评语",required = false)
    private String comment;

    /**
     * 导师认可，未审核0，不通过1，通过2，退回修改3
     */
    @ApiModelProperty(value = "导师认可，未审核0，之后3个按钮，不通过1，通过2，退回修改3",required = true)
    private Integer approval;

}
