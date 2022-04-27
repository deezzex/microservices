package com.deezzex.controller;

import com.deezzex.dto.LoginDto;
import com.deezzex.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

/**
 * The controller for login REST endpoints.
 * This class handles the CRUD operations for login, via HTTP actions.
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Slf4j
@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    /**
     * GET endpoint for getting customer from database by email for login
     * @param email customer username or email
     * @return response entity with OK HTTP status and DTO object which contains customer object for login
     **/
    @GetMapping(path = "/{username}")
    public ResponseEntity<LoginDto> getCustomerByEmail(@PathVariable("username") String email) {
        log.info("Try to get customer with email {}", email);

        LoginDto customer = service.findCustomerByEmail(email);

        if (customer.isExist()){
            log.info("Successfully founded customer with email {}", email);
        }else {
            log.error("Customer with email " + email + " doesn't exist");
        }

        return new ResponseEntity<>(customer, OK);
    }


}
