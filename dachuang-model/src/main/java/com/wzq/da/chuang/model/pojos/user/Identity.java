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
@Table(name = "`identity`")
@ApiModel(description = "身份表identity",value = "身份表identity")
public class Identity implements Serializable {
    /**
     * 身份id
     */
    @Id
    @Column(name = "identity_id")
    @ApiModelProperty(value = "身份ID",required = true)
    private String identityId;

    /**
     * 中文描述
     */
    @Column(name = "description")
    @ApiModelProperty(value = "中文描述，老师，学生，二级学院管理者等",required = true)
    private String description;

    /**
     * 英文名
     */
    @Column(name = "identity_name")
    @ApiModelProperty(value = "英文名",required = false)
    private String identityName;

    private static final long serialVersionUID = -461536780102945479L;
}