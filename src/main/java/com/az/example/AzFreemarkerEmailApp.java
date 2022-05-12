package com.az.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Az-FreeMarker-Email", version = "1.0",
        description = "It's a simple Email-API to send html formatted emails!"))
@SpringBootApplication
public class AzFreemarkerEmailApp {

    public static void main(String[] args) {
        SpringApplication.run(AzFreemarkerEmailApp.class, args);
    }
}