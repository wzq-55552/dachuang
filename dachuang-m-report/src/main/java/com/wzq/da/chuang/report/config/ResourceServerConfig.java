package com.wzq.da.chuang.report.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * Title：spring security资源服务配置
 * Description：需要实现2个configure方法，各自有一个资源id
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/18
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "res_report"; // 资源id，一个微服务一个

    @Resource
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                .tokenStore(tokenStore) // jwt方式，自带匹配客户端信息
                .stateless(true)
        ;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 确认客户端的范围，匹配认证服务器，不在范围就不能请求这个资源服务器
                // hasAny则可数组
                .antMatchers("/**").access("#oauth2.hasScope('all')") // 客户端有这个范围才可以访问
                .and().csrf().disable() // 关闭csrf，可以post
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //token方式，session绝不产生
                ;
    }
}
