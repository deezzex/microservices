package com.deezzex.service;

import com.deezzex.dto.RegisterRequest;
import com.deezzex.dto.RegisterResponse;
import com.deezzex.entity.Customer;
import com.deezzex.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public RegisterResponse createCustomer(RegisterRequest inputCustomer) {

        Customer customerForSave = Customer.builder()
                .firstName(inputCustomer.getFirstName())
                .lastName(inputCustomer.getLastName())
                .email(inputCustomer.getEmail())
                .birthDate(inputCustomer.getBirthDate())
                .build();

        Customer savedCustomer = repository.save(customerForSave);

        return RegisterResponse.builder()
                .id(savedCustomer.getId())
                .firstName(savedCustomer.getFirstName())
                .lastName(savedCustomer.getLastName())
                .email(savedCustomer.getEmail())
                .birthDate(savedCustomer.getBirthDate())
                .build();
    }
}
