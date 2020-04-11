package com.wzq.da.chuang.model.pojos.report;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "time_args")
@ApiModel(description = "时间参数表，中期报告是否过期",value = "时间参数表，中期报告是否过期")
public class TimeArgs implements Serializable {

    /**
     * 自增id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "自增id",required = true)
    private Long id;

    /**
     * 中期报告超时时间，yyyy-MM-dd hh:mm:ss
     */
    @Column(name = "m_out_date")
    @ApiModelProperty(value = "中期报告超时时间，yyyy-MM-dd hh:mm:ss",required = true)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date mOutDate;

    private static final long serialVersionUID = -3424494867890495693L;
}