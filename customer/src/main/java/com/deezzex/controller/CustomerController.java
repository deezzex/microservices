package com.deezzex.controller;

import com.deezzex.dto.RegisterRequest;
import com.deezzex.dto.RegisterResponse;
import com.deezzex.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        RegisterResponse savedCustomer = service.createCustomer(customer);

        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }
}
