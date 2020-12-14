package com.wzq.da.chuang.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Title：全局过滤器类
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/3/10
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    //令牌头名字
    private static final String AUTHORIZE_TOKEN = "Authorization";

    /***
     * 全局过滤器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取Request、Response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //获取请求的URI
        String path = request.getURI().getPath();

        //不在头文件中，则在这里封装到头文件中，资源服务器才可以访问，如果结合spring security的话，
        //都是放在请求头
        boolean hasToken = true;

        //1.获取头文件中的令牌信息
        String tokent = request.getHeaders().getFirst(AUTHORIZE_TOKEN);

        //2.如果头文件中没有，则从请求参数中获取
        if (StringUtils.isEmpty(tokent)) {
            tokent = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            hasToken = false;
        }

        //3.都没有则从cookie中获取
        if (StringUtils.isEmpty(tokent)) {
            HttpCookie cookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if (cookie!=null){
                tokent = cookie.getValue();
                hasToken = false;
            }
        }

        //如果全为空，没有令牌，则拦截
        if (StringUtils.isEmpty(tokent)) {
            //设置没有权限的状态码，401没有权限
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //返回
            return response.setComplete();
        }else{
            //有令牌
            //解析令牌数据
            //Claims claims = JwtUtil.parseJWT(tokent);
            //判断是否有前缀"bearer "
            if (!tokent.startsWith("bearer ") || !tokent.startsWith("Bearer ")){
                tokent = "bearer "+tokent;
            }
        }

        // 请求头没有，添加进去
        if (!hasToken){
            //将令牌封装到头文件中，让其访问其他资源服务器
            request.mutate().header(AUTHORIZE_TOKEN,tokent);
        }

        //放行
        return chain.filter(exchange);
    }


    /***
     * 过滤器执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
