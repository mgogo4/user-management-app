/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Swagger config configures and enables swagger UI.
 * Swagger provides tools that helps document, and consume RESTful Web services.
 * Starts with the application running with the 'swagger' profile.
 *
 * @author Michał Gogolewski
 */
@Configuration
@EnableSwagger2
@Profile("swagger")
public class SwaggerConfig {
    /**
     * Provides basic default configuration of Swagger.
     * Adds Accept-Language header to requests send on application endpoints with Swagger.
     * Specifies package scanned for REST endpoints.
     *
     * @return the docket.
     * @see Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(Collections.singletonList(new ParameterBuilder()
                        .name("Accept-Language")
                        .description("Accept Language")
                        .modelRef(new ModelRef("string"))
                        .defaultValue("pl-PL")
                        .parameterType("header")
                        .required(false)
                        .build()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.mgogo.user_management_app.rest.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}