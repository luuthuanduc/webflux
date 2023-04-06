package com.aimatrix.webflux.services;

import com.aimatrix.webflux.models.CustomerModel;
import com.aimatrix.webflux.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Override
    public Mono<CustomerModel> createCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<CustomerModel> updateCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Void> removeCustomer(String customerId) {
        return customerRepository.deleteById(customerId);
    }

    @Override
    public Mono<CustomerModel> findCustomerById(String customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Flux<CustomerModel> findAllCustomers() {
        return customerRepository.findAll();
    }
}
