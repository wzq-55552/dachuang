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
@ApiModel(value = "修改中期报告参数",description = "修改中期报告参数")
public class ReportUpdateParam implements Serializable {

    private static final long serialVersionUID = 8357452957846962761L;

    /**
     * 中期文件解释，类似encoder
     */
    @ApiModelProperty(value = "中期文件解释，类似encoder，加个输入框（文本编辑器）",required = false)
    private String content;

    /**
     * 自增id
     */
    @ApiModelProperty(value = "报告id",required = true)
    private Long reportId;

}
