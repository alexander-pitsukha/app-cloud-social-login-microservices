package com.alexander.services.category;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableJpaRepositories
@OpenAPIDefinition(info = @Info(title = "Category API", version = "1.0", description = "Documentation Category API v1.0"))
public class CategoryApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CategoryApplication.class);
        builder.run();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CategoryApplication.class);
    }

}
