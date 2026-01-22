package com.chaoyue.haodebar.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    /**
     * Swagger UI: http://localhost:8080/swagger-ui.html
     * OpenAPI JSON: http://localhost:8080/api-docs
     * @return
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("haodebar")
                        .version("1.0")
                        .description("hoadebar接口文档")
                        .contact(new Contact()
                                .name("开发团队")
                                .email("dev@haodebar.com")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}