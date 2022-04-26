package com.deezzex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Major class in spring boot application which run this service
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@SpringBootApplication
@EnableEurekaClient
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
