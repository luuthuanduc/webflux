package com.aimatrix.webflux.repositories;

import com.aimatrix.webflux.models.CustomerModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<CustomerModel, String> {

}
