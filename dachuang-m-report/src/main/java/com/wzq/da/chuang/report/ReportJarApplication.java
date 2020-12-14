package com.wzq.da.chuang.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wzq.da.chuang.model.mappers")
@EnableEurekaClient
public class ReportJarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportJarApplication.class,args);
    }

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
