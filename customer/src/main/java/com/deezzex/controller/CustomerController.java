package com.deezzex.controller;

import com.deezzex.dto.CustomerReadDto;
import com.deezzex.dto.RegisterRequest;
import com.deezzex.dto.RegisterResponse;
import com.deezzex.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RegisterResponse> registerCustomer(@RequestBody RegisterRequest customer) {
        log.info("Try to register new customer {}", customer.getEmail());

        RegisterResponse savedCustomer = service.createCustomer(customer);
        log.info("Customer successfully registered {}", savedCustomer.getEmail());

        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CustomerReadDto> getCustomer(@PathVariable("id") Long id) {
        log.info("Try to get customer with id {}", id);

        CustomerReadDto customer = service.getCustomer(id);

        log.info("Successfully founded customer with id {}", id);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
