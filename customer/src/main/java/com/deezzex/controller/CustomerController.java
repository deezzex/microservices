package com.deezzex.controller;

import com.deezzex.dto.CustomerReadDto;
import com.deezzex.dto.RegisterRequest;
import com.deezzex.dto.RegisterResponse;
import com.deezzex.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * The controller for customer REST endpoints.
 * This class handles the CRUD operations for customers, via HTTP actions.
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    /**
     * POST endpoint for creating new customer from JSON request body
     * @param customer json object from request body
     * @return response entity with CREATED HTTP status and DTO created object
     **/
    @PostMapping
    public ResponseEntity<RegisterResponse> registerCustomer(@RequestBody RegisterRequest customer) {
        log.info("Try to register new customer {}", customer.getEmail());

        RegisterResponse savedCustomer = service.createCustomer(customer);
        log.info("Customer successfully registered {}", savedCustomer.getEmail());

        return new ResponseEntity<>(savedCustomer, CREATED);
    }

    /**
     * GET endpoint for getting customer from database by id
     * @param id customer id
     * @return response entity with OK HTTP status and DTO object which contains customer object
     **/
    @GetMapping(path = "{id}")
    public ResponseEntity<CustomerReadDto> getCustomer(@PathVariable("id") Long id) {
        log.info("Try to get customer with id {}", id);

        CustomerReadDto customer = service.getCustomer(id);

        log.info("Successfully founded customer with id {}", id);

        return new ResponseEntity<>(customer, OK);
    }

    /**
     * PUT endpoint for updating customer from database by id
     * @param id customer id
     * @param customer JSON data from request body for updating existed customer
     * @return response entity with OK HTTP status and DTO object which contains updated customer object
     **/
    @PutMapping(path = "{id}")
    public ResponseEntity<CustomerReadDto> updateCustomer(@PathVariable("id") Long id,
                                                          @RequestBody CustomerReadDto customer) {
        log.info("Try to update customer with id {}", id);

        CustomerReadDto updatedCustomer = service.updateCustomer(id, customer);

        log.info("Successfully updated customer with id {}", id);

        return new ResponseEntity<>(updatedCustomer, OK);
    }

    /**
     * DELETE endpoint for deleting customer from database by id
     * @param id customer id
     * @return response entity with OK HTTP status and boolean which shows result of deleting
     **/
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable("id") Long id) {
        log.info("Try to delete customer with id {}", id);

        boolean isDeleted = service.deleteCustomer(id);

        log.info("Successfully deleted customer with id {}", id);

        return new ResponseEntity<>(isDeleted, OK);
    }
}
