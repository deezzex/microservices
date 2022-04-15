package com.deezzex.controller;

import com.deezzex.dto.CustomerReadDto;
import com.deezzex.dto.RegisterRequest;
import com.deezzex.dto.RegisterResponse;
import com.deezzex.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<RegisterResponse> registerCustomer(@RequestBody RegisterRequest customer) {
        log.info("Try to register new customer {}", customer.getEmail());

        RegisterResponse savedCustomer = service.createCustomer(customer);
        log.info("Customer successfully registered {}", savedCustomer.getEmail());

        return new ResponseEntity<>(savedCustomer, CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CustomerReadDto> getCustomer(@PathVariable("id") Long id) {
        log.info("Try to get customer with id {}", id);

        CustomerReadDto customer = service.getCustomer(id);

        log.info("Successfully founded customer with id {}", id);

        return new ResponseEntity<>(customer, OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<CustomerReadDto> updateCustomer(@PathVariable("id") Long id,
                                                          @RequestBody CustomerReadDto customer) {
        log.info("Try to update customer with id {}", id);

        CustomerReadDto updatedCustomer = service.updateCustomer(id, customer);

        log.info("Successfully updated customer with id {}", id);

        return new ResponseEntity<>(updatedCustomer, OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable("id") Long id) {
        log.info("Try to delete customer with id {}", id);

        boolean isDeleted = service.deleteCustomer(id);

        log.info("Successfully deleted customer with id {}", id);

        return new ResponseEntity<>(isDeleted, OK);
    }
}
