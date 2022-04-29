package com.deezzex.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

/**
 * Configuration class for API GATEWAY service
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Configuration
public class GatewayConfig {

    /**
     * Definition route locator bean for adjusting routes of gateway and added some filters to it
     * @param builder a RouteLocatorBuilder for building route locator object
     * @return route locator, built object
     **/
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/customers/**")
                        .filters(f -> f/*.circuitBreaker(config -> config.setName("CUSTOMER-SERVICE").setFallbackUri("redirect:/customerFallback"))*/
                                .requestRateLimiter().configure(config -> config.setRateLimiter(redisRateLimiter())))
                        .uri("http://localhost:8080/"))
                .route(r -> r.path("/api/v1/accounts/**")
                        .filters(f -> f/*.circuitBreaker(config -> config.setName("ACCOUNT-SERVICE").setFallbackUri("redirect:/accountFallback"))*/
                                .requestRateLimiter().configure(config -> config.setRateLimiter(redisRateLimiter())))
                        .uri("http://localhost:8081/"))
                .route(r -> r.path("/api/v1/mails/**")
                        .uri("http://localhost:8084/"))
                .build();
    }

    /**
     * Definition redis rate limiter bean for adjusting throttling on routes
     * @return redis rate limiter, built object
     **/
    @Bean
    public RedisRateLimiter redisRateLimiter()
    {
        return new RedisRateLimiter(3,3);
    }

    /**
     * Definition key resolver bean for adjusting pluggable strategies derive the key for limiting requests.
     * @return key resolver, built object
     **/
    @Bean
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    /**
     * Definition rest template bean for communication between services.
     * @return rest template, built object
     **/
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
