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
@Table(name = "p_range")
@ApiModel(description = "权限关联表p_range",value = "权限关联表p_range")
public class PRange implements Serializable {
    /**
     * 身份角色ID
     */
    @Column(name = "identity_id")
    @ApiModelProperty(value = "身份角色ID",required = true)
    private String identityId;

    /**
     * 权限ID
     */
    @Column(name = "p_id")
    @ApiModelProperty(value = "权限ID",required = true)
    private String pId;

    private static final long serialVersionUID = -5067141959636012327L;
}