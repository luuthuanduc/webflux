package com.aimatrix.webflux.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.List;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@Configuration
class CustomerRouter {

    @Bean
    RouterFunction<ServerResponse> customerEndpoint(CustomerHandler handler) {
        return RouterFunctions.nest(path("/router"), RouterFunctions
            .route(POST("/customers").and(accept(MediaType.APPLICATION_JSON)), handler::postCustomer)
            .andRoute(GET("/customers").and(accept(MediaType.APPLICATION_JSON)), handler::getAllCustomers)
            .andRoute(GET("/customers/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getCustomerById)
            .andRoute(DELETE("/customers/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::deleteCustomer)
            .andRoute(PUT("/customers/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::putCustomer)
        );
    }

    @Bean
    CorsWebFilter corsWebFilter() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8080"));
        configuration.setAllowedMethods(List.of("POST", "GET", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("X-USER-ID"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return new CorsWebFilter(source);
    }

}
