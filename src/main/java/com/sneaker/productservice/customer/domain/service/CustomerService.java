package com.sneaker.productservice.customer.domain.service;

import com.sneaker.productservice.customer.domain.entity.CustomerEntity;
import com.sneaker.productservice.customer.domain.entity.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Optional<CustomerEntity> getCustomerByEmail(String email) {
        return repository.findByEmail(email);
    }

    public CustomerEntity save(CustomerEntity newCustomer) {
        return repository.save(newCustomer);
    }
}
