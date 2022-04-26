package com.deezzex.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for account service
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Configuration
public class AccountConfiguration {

    /**
     * Definition rest template bean for communication between services
     * @param builder a RestTemplateBuilder for building rest template object
     * @return restTemplate, built object
     **/
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
