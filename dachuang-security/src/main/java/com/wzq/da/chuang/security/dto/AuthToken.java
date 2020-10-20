package com.wzq.da.chuang.security.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Title：令牌DTO
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/3/11
 */
public class AuthToken implements Serializable {

    //令牌信息
    @ApiModelProperty(value = "Token",required = true)
    String accessToken;

    //刷新token(refresh_token)
    @ApiModelProperty(value = "刷新token",required = true)
    String refreshToken;

    //令牌信息
    @ApiModelProperty(value = "身份信息，1是学生，2是指导老师，3是二级学院，4是大创管理，5是专家",required = true)
    String identityId;

    //jwt短令牌
    @ApiModelProperty(value = "jwt短令牌",required = true)
    String jti;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }
}
