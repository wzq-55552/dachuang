package com.wzq.da.chuang.model.pojos.user;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`user`")
@ApiModel(description = "用户表user",value = "用户表user")
public class UserInformation implements Serializable {

    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    @ApiModelProperty(value = "用户ID，学号、工作id",required = true)
    private String userId;

    /**
     * 用户密码
     */
    @Column(name = "`password`")
    @ApiModelProperty(value = "用户密码",required = true)
    @JsonIgnore
    private String password;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    @ApiModelProperty(value = "用户姓名",required = false)
    private String userName;

    /**
     * 用户手机，11位
     */
    @Column(name = "phone")
    @ApiModelProperty(value = "用户手机，11位",required = true)
    private String phone;

    /**
     * 身份角色ID
     */
    @Column(name = "identity_id")
    @ApiModelProperty(value = "身份角色ID",required = true)
    private String identityId;

    /**
     * 学院ID
     */
    @Column(name = "collegeId")
    @ApiModelProperty(value = "学院ID",required = true)
    private Long collegeId;

    private static final long serialVersionUID = 8106953024437786486L;
}