package com.wzq.da.chuang.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Title：服务安全配置
 * Description：拦截器功能,认证过程，全局方法拦截，都得先经过这个配置
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/16
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)//数据通过类型
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //密码编码器，密码匹配的加密算法
    //BCrypt随机不可解加密算法
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //安全密码，有md4.md5,SHA-1,SHA-256
    }

    // 配置认证管理器，必须在这个类中配置
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    // 忽略该地址，无需携带token，直接就可以被访问授权，静态资源可以直接被访问
    // 资源服务器需要该地址来验证token是否符合
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth/check_token")
                .antMatchers("/oauth/login")
                .antMatchers("/oauth/refresh/token")
                .antMatchers("/createImg/**")
                .antMatchers("/oauth/logout");
    }

    //自己实现用户权限认证，绑定数据库
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetalisServiceImpl();
    }

    //设置用户
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //连接数据库的用户
        auth.userDetailsService(userDetailsService());
    }

}
