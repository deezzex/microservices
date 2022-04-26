package com.deezzex.service;

import com.deezzex.customer.Customer;
import com.deezzex.customer.AccountWithCustomer;
import com.deezzex.dto.CreateRequest;
import com.deezzex.dto.CreateResponse;
import com.deezzex.entity.Account;
import com.deezzex.exception.AccountException;
import com.deezzex.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

/**
 * Service layer class for computing accounts
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository repository;
    private final RestTemplate restTemplate;

    /**
     * Method for creating new account in DB
     * @exception AccountException if something went wrong in saving data in DB
     * @param inputAccount account data from request
     * @return response with data about successfully created account
     **/
    @Transactional
    public CreateResponse createAccount(CreateRequest inputAccount) {
        try {
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

        }catch (Exception e) {
            throw new AccountException(HttpStatus.BAD_REQUEST, "Couldn't create account");
        }
    }

    /**
     * Method for getting account and appropriate customer by account id
     * @exception AccountException if something went wrong in getting account or customer from other service
     * @param accountId id of account which we want to get
     * @return response with data about account and its owner
     **/
    public AccountWithCustomer getAccountWithOwner(Long accountId) {
        try {
            Optional<Account> maybeAccount = repository.findById(accountId);

            if (maybeAccount.isEmpty()){
                throw new AccountException(HttpStatus.BAD_REQUEST,
                        "Account with id " + accountId + " doesn't exist");
            }

            Account account = maybeAccount.get();

            Customer customer = restTemplate.getForObject("http://CUSTOMER-SERVICE/api/v1/customers/{customerId}",
                    Customer.class, account.getCustomerId());

            if (Objects.nonNull(customer)){
                customer.setId(account.getCustomerId());
            }

            return AccountWithCustomer.builder()
                    .account(account)
                    .customer(customer)
                    .build();

        }catch (Exception e) {
            throw new AccountException(HttpStatus.BAD_REQUEST, "Couldn't get account with owner");
        }
    }
}
