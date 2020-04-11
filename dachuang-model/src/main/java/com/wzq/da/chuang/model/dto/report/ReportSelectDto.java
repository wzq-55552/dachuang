package com.wzq.da.chuang.model.dto.report;

import com.wzq.da.chuang.model.pojos.report.MFile;
import com.wzq.da.chuang.model.pojos.report.MReport;
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
@ApiModel(value = "根据项目id返回中期报告内容和文件",description = "根据项目id返回中期报告内容和文件")
public class ReportSelectDto implements Serializable {

    private static final long serialVersionUID = 2309651760732996944L;

    @ApiModelProperty(value = "项目名字，可能需要显示",required = false)
    private String projectName;

    @ApiModelProperty(value = "负责人名字，可能需要显示",required = false)
    private String userName;

    @ApiModelProperty(value = "指导老师名字，可能需要显示",required = false)
    private String teacherName;

    @ApiModelProperty(value = "专家名字，可能需要显示",required = false)
    private String expertName;

    @ApiModelProperty(value = "中期报告的内容",required = false)
    private MReport mReport;

    @ApiModelProperty(value = "中期报告的文件，是一个集合",required = false)
    private List<MFile> mFiles;

}
