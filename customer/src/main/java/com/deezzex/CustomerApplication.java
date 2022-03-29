package com.deezzex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CustomerApplication {
    public static void main(String[] args) {
        System.setProperty("spring.application.name", "CUSTOMER-SERVICE");
        SpringApplication.run(CustomerApplication.class, args);
    }
}
