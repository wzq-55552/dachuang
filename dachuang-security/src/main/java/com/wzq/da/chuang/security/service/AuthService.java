package com.wzq.da.chuang.security.service;

import com.wzq.da.chuang.security.dto.AuthToken;
import com.wzq.da.chuang.security.dto.LoginDto;

public interface AuthService {

    /***
     * 授权认证方法
     */
    AuthToken login(LoginDto loginDto, String clientId, String clientSecret);

    /**
     * 刷新令牌
     * @param refresh_token
     * @param clientId
     * @param clientSecret
     * @return
     */
    AuthToken refreshToken(String refresh_token, String clientId, String clientSecret);
}
