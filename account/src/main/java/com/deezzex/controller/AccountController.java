package com.deezzex.controller;

import com.deezzex.customer.ResponseTemplate;
import com.deezzex.dto.CreateRequest;
import com.deezzex.dto.CreateResponse;
import com.deezzex.entity.Account;
import com.deezzex.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateResponse> createAccount(@RequestBody CreateRequest inputAccount) {
        CreateResponse createdAccount = service.createAccount(inputAccount);
        return new ResponseEntity<>(createdAccount, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ResponseTemplate> getAccount(@PathVariable("id") Long id) {
        ResponseTemplate accountWithOwner = service.getAccountWithOwner(id);
        return new ResponseEntity<>(accountWithOwner, HttpStatus.OK);
    }
}
