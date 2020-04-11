package com.wzq.da.chuang.model.pojos.user;

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
@Table(name = "college")
@ApiModel(description = "学院表college",value = "学院表college")
public class College implements Serializable {
    /**
     * 自增学院id
     */
    @Id
    @Column(name = "collegeId")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "自增学院id",required = true)
    private Long collegeId;

    /**
     * 学院名字
     */
    @Column(name = "collegeName")
    @ApiModelProperty(value = "学院名字",required = true)
    private String collegeName;

    private static final long serialVersionUID = 1L;
}