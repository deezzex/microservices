package com.deezzex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        System.setProperty("spring.application.name", "API-GATEWAY");
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
