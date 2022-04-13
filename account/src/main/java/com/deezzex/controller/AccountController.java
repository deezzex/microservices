package com.deezzex.controller;

import com.deezzex.customer.ResponseTemplate;
import com.deezzex.dto.CreateRequest;
import com.deezzex.dto.CreateResponse;
import com.deezzex.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping
    public ResponseEntity<CreateResponse> createAccount(@RequestBody CreateRequest inputAccount) {
        log.info("Try to create new account {}", inputAccount.getName());

        CreateResponse createdAccount = service.createAccount(inputAccount);

        log.info("Account successfully created {}", inputAccount.getName());

        return new ResponseEntity<>(createdAccount, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ResponseTemplate> getAccount(@PathVariable("id") Long id) {
        log.info("Try to get account with id {}", id);

        ResponseTemplate accountWithOwner = service.getAccountWithOwner(id);

        log.info("Successfully founded account with id {}", id);

        return new ResponseEntity<>(accountWithOwner, HttpStatus.OK);
    }
}
