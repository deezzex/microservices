package com.deezzex.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/customers/**")
                        .filters(f -> f.circuitBreaker(config -> config.setName("CUSTOMER-SERVICE").setFallbackUri("redirect:/customerFallback"))
                                .requestRateLimiter().configure(config -> config.setRateLimiter(redisRateLimiter())))
                        .uri("http://localhost:8080/"))
                .route(r -> r.path("/api/v1/accounts/**")
                        .filters(f -> f.circuitBreaker(config -> config.setName("ACCOUNT-SERVICE").setFallbackUri("redirect:/accountFallback"))
                                .requestRateLimiter().configure(config -> config.setRateLimiter(redisRateLimiter())))
                        .uri("http://localhost:8082/"))
                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter()
    {
        return new RedisRateLimiter(1,2);
    }

    @Bean
    KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }
}
