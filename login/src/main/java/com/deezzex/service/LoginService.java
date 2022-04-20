package com.deezzex.service;

import com.deezzex.dto.LoginDto;
import com.deezzex.entity.Customer;
import com.deezzex.exception.LoginException;
import com.deezzex.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final CustomerRepository repository;

    public LoginDto findCustomerByEmail(String email){
        try {
            Optional<Customer> maybeCustomer = repository.findByEmail(email);

            if (maybeCustomer.isEmpty()){
                throw new LoginException(HttpStatus.BAD_REQUEST, "Customer with with email: " + email + " not exists");
            }

            Customer customer = maybeCustomer.get();

            return LoginDto.builder()
                    .id(customer.getId())
                    .username(customer.getEmail())
                    .password(customer.getPassword())
                    .role("ADMIN")
                    .isExist(true)
                    .build();

        }catch (Exception e) {
            return LoginDto.builder()
                    .isExist(false)
                    .build();
        }
    }
}
