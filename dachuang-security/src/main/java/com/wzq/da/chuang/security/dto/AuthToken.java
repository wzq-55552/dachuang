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
}
