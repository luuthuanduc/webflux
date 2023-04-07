package com.aimatrix.webflux.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

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

}
