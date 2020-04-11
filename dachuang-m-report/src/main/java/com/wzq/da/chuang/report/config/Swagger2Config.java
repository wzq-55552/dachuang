package com.wzq.da.chuang.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Title：Swagger2公共配置
 * Description：API在线文档
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/25
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)//.groupName("user微服务") // 分组名
                // 指定构建api文档的详细信息的方法：apiInfo()
                .apiInfo(apiInfo())
                //.enable(false) // 是否开启Swagger2，发布环境就要false，默认true
                .select()
                // 指定要生成api接口的包路径，.basePackage把controller作为包路径，生成controller中的所有接口
                .apis(RequestHandlerSelectors.basePackage("com.wzq.da.chuang.report.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    // localhost:9003/swagger-ui.html
    /**
     * 构建api文档的详细信息，作者信息，标题等
     * @return
     */
    private ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("吴泽强","https://www.baidu.com/","1073120596@qq.com");
        // ApiInfo只有一个构造器
        return new ApiInfo(
                "惠州学院大创中期子模块在线API文档",
                "Swagger2在线API文档",
                "v1.0",
                "https://www.baidu.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
