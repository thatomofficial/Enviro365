package com.enviro.assessment.grad001.thato_mokgotsi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Configuration class for Swagger API documentation
 */
@Configuration
public class SwaggerConfig {

    /**
     * Swagger configuration
     * @return OpenAPI
     */
    @Bean
    @Primary
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Waste Sorting API")
                        .version("1.0")
                        .description("API documentation for Waste Sorting application"));
    }

    /**
     * This method creates an OpenAPI object that describes the API.
     * @return OpenAPI object
     */
    @Bean

    public OpenAPI publicApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Waste Sorting API")
                        .version("1.0")
                        .description("API documentation for Waste Sorting application"));
    }
}