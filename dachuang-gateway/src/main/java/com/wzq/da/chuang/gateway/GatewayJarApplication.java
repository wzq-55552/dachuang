package com.wzq.da.chuang.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
public class GatewayJarApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayJarApplication.class,args);
    }

    /***
     * IP限流
     * @return
     */
    @Bean(name="ipKeyResolver")
    public KeyResolver userKeyResolver() {
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                //获取远程客户端IP
                String hostName = exchange.getRequest().getRemoteAddress().getHostString();
                System.out.println("访问的hostName:"+hostName);
                return Mono.just(hostName);
            }
        };
    }

}
