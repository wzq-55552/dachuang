package com.wzq.da.chuang.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Title：这里验证token
 * Description：默认普通令牌
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/17
 */
@Configuration
public class TokenConfig {

    // 秘钥（盐），字符串dachaung加密后作为盐
    private String SIGNING_KEY = "$2a$10$X9dkRlCYHf/ukex/WkWDou1xCBf256CWeoQ7qPEjBVhWgsqLUgmb2";

    @Bean
    public TokenStore tokenStore(){
        // JWT令牌
        return new JwtTokenStore(accessTokenConverter());
    }

    // 3部分，头部（Base64），内容（Base64），签名（头部算法加密，一般是HS256）
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY); // 对称秘钥，不是非对称加密，非对称加密需要公钥和私钥，私钥给认证服务器，公钥给资源服务器
        return converter;
    }

}
