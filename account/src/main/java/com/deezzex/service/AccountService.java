package com.deezzex.service;

import com.deezzex.customer.Customer;
import com.deezzex.customer.ResponseTemplate;
import com.deezzex.dto.CreateRequest;
import com.deezzex.dto.CreateResponse;
import com.deezzex.entity.Account;
import com.deezzex.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository repository;
    private final RestTemplate restTemplate;

    @Autowired
    public AccountService(AccountRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public CreateResponse createAccount(CreateRequest inputAccount) {
        Account account = Account.builder()
                .name(inputAccount.getName())
                .amount(inputAccount.getAmount())
                .customerId(inputAccount.getCustomerId())
                .build();

        Account savedAccount = repository.save(account);

        return CreateResponse.builder()
                .id(savedAccount.getId())
                .name(savedAccount.getName())
                .amount(savedAccount.getAmount())
                .customerId(savedAccount.getCustomerId())
                .build();
    }

    public ResponseTemplate getAccountWithOwner(Long accountId) {
        Optional<Account> maybeAccount = repository.findById(accountId);
        Account account = maybeAccount.get();

        Customer customer = restTemplate.getForObject("http://CUSTOMER-SERVICE/api/v1/customers/{customerId}",
                Customer.class, account.getCustomerId());

        if (Objects.nonNull(customer)){
            customer.setId(account.getCustomerId());
        }

        return ResponseTemplate.builder()
                .account(account)
                .customer(customer)
                .build();
    }
}
