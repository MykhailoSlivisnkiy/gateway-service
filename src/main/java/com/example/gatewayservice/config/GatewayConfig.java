package com.example.gatewayservice.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("telegram-service", r -> r.path("/telegram/**").filters(f -> f.filter(filter)).uri("http://localhost:8081/"))
                .route("subscription-service", r -> r.path("/subscription/**").filters(f -> f.filter(filter)).uri("http://localhost:8082/"))
                .route("goods-service", r -> r.path("/main/**").filters(f -> f.filter(filter)).uri("http://localhost:8084/"))
                .route("email-service", r -> r.path("/email/**").filters(f -> f.filter(filter)).uri("http://localhost:8085/"))
                .route("auth-service", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("http://localhost:8083/"))
                .build();
    }
}
