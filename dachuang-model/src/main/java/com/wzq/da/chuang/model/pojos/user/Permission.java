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
@Table(name = "permission")
@ApiModel(description = "权限表permission",value = "权限表permission")
public class Permission implements Serializable {

    /**
     * 权限ID
     */
    @Id
    @Column(name = "p_id")
    @ApiModelProperty(value = "权限ID",required = true)
    private String pId;

    /**
     * 权限描述
     */
    @Column(name = "description")
    @ApiModelProperty(value = "权限描述",required = true)
    private String description;

    /**
     * 链接地址
     */
    @Column(name = "url")
    @ApiModelProperty(value = "链接地址",required = true)
    private String url;

    /**
     * 权限名
     */
    @Column(name = "p_name")
    @ApiModelProperty(value = "权限名，权限框架用到",required = true)
    private String pName;

    private static final long serialVersionUID = -2907864207249490654L;
}