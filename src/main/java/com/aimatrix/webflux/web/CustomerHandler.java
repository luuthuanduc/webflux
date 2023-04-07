package com.aimatrix.webflux.web;

import com.aimatrix.webflux.models.CustomerModel;
import com.aimatrix.webflux.services.CustomerService;
import com.aimatrix.webflux.validation.CustomerValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
class CustomerHandler {

    CustomerService customerService;
    CustomerValidator customerValidator;

    Mono<ServerResponse> postCustomer(ServerRequest request) {
        return request.bodyToMono(CustomerModel.class)
            .flatMap(customerValidator::validate)
            .flatMap(customerService::createCustomer)
            .flatMap(result -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(result))
            .onErrorResume(err -> ServerResponse.badRequest().bodyValue(err.getMessage()));
    }

    Mono<ServerResponse> putCustomer(ServerRequest request) {
        return request.bodyToMono(CustomerModel.class)
            .flatMap(customerValidator::validate)
            .flatMap(customerService::updateCustomer)
            .flatMap(result -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(result))
            .onErrorResume(err -> ServerResponse.badRequest().bodyValue(err.getMessage()));
    }

    Mono<ServerResponse> deleteCustomer(ServerRequest request) {
        return customerService.removeCustomer(request.pathVariable("id"))
            .then(Mono.defer(() -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .build()))
            .onErrorResume(err -> ServerResponse.badRequest().build());
    }

    Mono<ServerResponse> getCustomerById(ServerRequest request) {
        return customerService.findCustomerById(request.pathVariable("id"))
            .flatMap(result -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(result))
            .switchIfEmpty(ServerResponse.notFound().build());

    }

    Mono<ServerResponse> getAllCustomers(ServerRequest request) {
        return customerService.findAllCustomers()
            .collectList()
            .flatMap(result -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(result));
    }


}
