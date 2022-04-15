package com.deezzex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
        // TODO: Enable rate limiting on the API gateway
        // TODO: Security https://medium.com/@mool.smreeti/microservices-with-spring-boot-authentication-with-jwt-and-spring-security-6e10155d9db0
    }
}
