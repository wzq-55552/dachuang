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
@ApiModel(value = "中期报告生成参数，前端给我",description = "中期报告生成参数，前端给我")
public class ReportInsertParam implements Serializable {

    private static final long serialVersionUID = 521575954725556698L;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人id",required = true)
    private String userId;

    /**
     * 中期文件解释，类似encoder
     */
    @ApiModelProperty(value = "中期文件解释，类似encoder，加个输入框（文本编辑器）",required = false)
    private String content;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id",required = true)
    private Long projectId;

}
