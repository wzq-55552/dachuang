package com.wzq.da.chuang.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Title：spring security授权认证服务器，旭日项目使用密码模式就可以啦
 * Description：数据不保存到内存，保存在数据库中，提高服务器效率
 *              AuthorizationServerConfigurerAdapter需要实现3个configure方法
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/16
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    // 注入认证管理器，密码模式需要用到
    @Resource
    private AuthenticationManager authenticationManager;

    // 注入授权码模式，但是这个不会采用，以后可能需要
    @Resource
    private AuthorizationCodeServices authorizationCodeServices;

    // 注入自定义JWT令牌格式
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    // 自动识别数据库中的表
    @Bean
    @Primary // 默认配置了数据源，加这个注释是为了启用CP数据源
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        // 配置数据源（注意，我使用的是 HikariCP 连接池），以上注解是指定数据源，否则会有冲突
        return DataSourceBuilder.create().build();
    }

    @Bean
    public TokenStore tokenStore() {
        // 基于 JDBC 实现，令牌保存到数据库，默认InMemory内存方式
        return new JdbcTokenStore(dataSource());
    }

    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        // 基于 JDBC 实现，需要事先在数据库配置客户端信息
        // 带上client id 和秘钥
        return new JdbcClientDetailsService(dataSource());
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        // 配置授权码模式
        return new JdbcAuthorizationCodeServices(dataSource());
    }

    /**
     * 令牌信息设置
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(jdbcClientDetailsService()); // 客户端信息
        services.setSupportRefreshToken(true); // 支持刷新令牌
        services.setTokenStore(tokenStore()); // 令牌存储方式==》》数据库

        // 设置JWT令牌
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);

        //令牌有效期设置在数据库
        //services.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        //services.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天

        return services;
    }

    /**
     * 设置令牌存储模式
     * 授权方式采用密码模式即可
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager) // 认证服务器
                .authorizationCodeServices(authorizationCodeServices) // 授权码服务
                .tokenServices(tokenServices()) // 令牌生成跟存储方式等配置
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); // 允许POST请求
    }

    /**
     * 客户端配置，这里采用数据库模式
     * 客户端id 跟 秘钥 ==》》数据库，秘钥要BCrypt加密到数据库，发请求的秘钥不需要加密
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
    }

    /**
     * 配置令牌访问的安全约束
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()") // oauth/token_key是公开的，资源服务器获取公钥
                .checkTokenAccess("permitAll()") // oauth/check_token公开，资源服务器通过这个url验证token
                .allowFormAuthenticationForClients(); // 允许表单认证（申请令牌）
    }

}
