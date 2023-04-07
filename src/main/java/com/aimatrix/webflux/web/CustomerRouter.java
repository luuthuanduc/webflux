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
        return RouterFunctions.nest(path("/nest"), RouterFunctions
            .route(POST("/customer").and(accept(MediaType.APPLICATION_JSON)), handler::postCustomer)
            .andRoute(GET("/customers").and(accept(MediaType.APPLICATION_JSON)), handler::getAllCustomers)
            .andRoute(GET("/customer/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getCustomerById)
            .andRoute(DELETE("/customer/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::deleteCustomer)
            .andRoute(PUT("/customer/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::putCustomer)
        );
    }

}
