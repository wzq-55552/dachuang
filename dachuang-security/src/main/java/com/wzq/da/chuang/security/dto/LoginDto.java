package com.wzq.da.chuang.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Title：登录dto
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/3/18
 */
@ApiModel(value = "登录dto",description = "登录dto")
public class LoginDto implements Serializable {

    private static final long serialVersionUID = -5924360569223645433L;

    @ApiModelProperty(value = "用户id",required = true)
    String userID;

    @ApiModelProperty(value = "用户密码",required = true)
    String userPsw;

    //验证码
    @ApiModelProperty(value = "验证码",required = true)
    String randString;

    public String getRandString() {
        return randString;
    }

    public void setRandString(String randString) {
        this.randString = randString;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userId) {
        this.userID = userId;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

}
