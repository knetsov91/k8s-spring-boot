package com.ko.k8sspringboot.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        String schema = "JWT";
        return  new OpenAPI()
                .info(new Info().title("Project API"))
                .addSecurityItem(new SecurityRequirement().addList(schema))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        schema,
                                        new SecurityScheme()
                                                .name("Bearer")
                                                .scheme("bearer")
                                                .type(SecurityScheme.Type.HTTP)
                                                .bearerFormat("JWT")
                                )
                );

    }
}
