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
@Table(name = "m_file")
@ApiModel(description = "文件表m_file",value = "文件表m_file")
public class MFile implements Serializable {
    /**
     * 自增id
     */
    @Id
    @Column(name = "f_id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "自增ID",required = true)
    private Long fId;

    /**
     * 文件路径
     */
    @Column(name = "f_url")
    @ApiModelProperty(value = "文件路径",required = true)
    private String fUrl;

    /**
     * 文件名
     */
    @Column(name = "f_name")
    @ApiModelProperty(value = "文件名",required = true)
    private String fName;

    /**
     * 文件类型，pdf、doc
     */
    @Column(name = "f_type")
    @ApiModelProperty(value = "文件类型",required = false)
    private String fType;

    /**
     * 是该中期报告的文件之一
     */
    @Column(name = "report_id")
    @ApiModelProperty(value = "是该中期报告的文件之一",required = true)
    private Long reportId;

    private static final long serialVersionUID = -3075008978169365430L;
}