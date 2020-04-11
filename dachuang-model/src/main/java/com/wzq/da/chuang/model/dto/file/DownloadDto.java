package com.wzq.da.chuang.model.dto.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "下载文件dto",value = "下载文件dto")
public class DownloadDto implements Serializable {

    private static final long serialVersionUID = -434377331557361280L;

    @ApiModelProperty(value = "文件路径",required = true)
    private String fileUrl;

    @ApiModelProperty(value = "文件名",required = true)
    private String fileName;

}
