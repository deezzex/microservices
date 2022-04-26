package com.deezzex.controller;

import com.deezzex.customer.AccountWithCustomer;
import com.deezzex.dto.CreateRequest;
import com.deezzex.dto.CreateResponse;
import com.deezzex.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * The controller for account REST endpoints.
 * This class handles the CRUD operations for accounts, via HTTP actions.
 * @version 1
 * @author Sviatoslav Pshtir
 **/
@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    /**
     * POST endpoint for creating new account from JSON request body
     * @param inputAccount input json object from request body
     * @return response entity with CREATED HTTP status and DTO created object
     **/
    @PostMapping
    public ResponseEntity<CreateResponse> createAccount(@RequestBody CreateRequest inputAccount) {
        log.info("Try to create new account {}", inputAccount.getName());

        CreateResponse createdAccount = service.createAccount(inputAccount);

        log.info("Account successfully created {}", inputAccount.getName());

        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    /**
     * GET endpoint for getting account with related owner from database by account id
     * @param id account id
     * @return response entity with OK HTTP status and DTO object which contains account and related customer
     **/
    @GetMapping(path = "{id}")
    public ResponseEntity<AccountWithCustomer> getAccount(@PathVariable("id") Long id) {
        log.info("Try to get account with id {}", id);

        AccountWithCustomer accountWithOwner = service.getAccountWithOwner(id);

        log.info("Successfully founded account with id {}", id);

        return new ResponseEntity<>(accountWithOwner, HttpStatus.OK);
    }
}
