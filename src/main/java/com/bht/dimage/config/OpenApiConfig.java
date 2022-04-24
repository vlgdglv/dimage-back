package com.bht.dimage.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class OpenApiConfig {
    @Bean(value = "indexApi")
    public Docket indexApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("前端接口分组").apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bht.dimage.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Dimage　后端ＡＰＩ")
                .description("毕设-分布式图片分享交易平台Dimage")
                .version("1.0.1")
                .build();
    }
}
