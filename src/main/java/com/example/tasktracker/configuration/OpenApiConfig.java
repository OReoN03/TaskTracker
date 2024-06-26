package com.example.tasktracker.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI cinemaProject() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task tracker")
                        .description("REST task tracker")
                        .version("0.1"));
    }
}
