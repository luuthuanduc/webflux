package com.aimatrix.webflux.web;

import com.aimatrix.webflux.models.CustomerModel;
import com.aimatrix.webflux.services.CustomerService;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping(
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestController {

    CustomerService customerService;

    @GetMapping("/customers/{id}")
    Publisher<ResponseEntity<CustomerModel>> getCustomerById(@PathVariable("id") String id) {
        return customerService.findCustomerById(id)
            .map(ResponseEntity::ok)
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping("/customers")
    Publisher<ResponseEntity<CustomerModel>> postCustomer(@RequestBody CustomerModel customer) {
        return customerService.createCustomer(customer).map(ResponseEntity::ok);
    }

    @PutMapping("/customers")
    Publisher<ResponseEntity<CustomerModel>> putCustomer(@RequestBody CustomerModel customer) {
        return customerService.updateCustomer(customer).map(ResponseEntity::ok);
    }

    @GetMapping("/customers")
    Publisher<CustomerModel> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @DeleteMapping("/customers/{id}")
    Publisher<Void> deleteCustomer(@PathVariable("id") String id) {
        return customerService.removeCustomer(id);
    }
}
