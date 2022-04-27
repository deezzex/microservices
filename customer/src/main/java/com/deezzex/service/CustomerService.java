package com.deezzex.service;

import com.deezzex.dto.CustomerReadDto;
import com.deezzex.dto.RegisterRequest;
import com.deezzex.dto.RegisterResponse;
import com.deezzex.entity.Customer;
import com.deezzex.exception.CustomerException;
import com.deezzex.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service layer class for computing customers
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Method for creating new customer in DB
     * @exception CustomerException if something went wrong in saving data in DB
     * @param inputCustomer customer data from request
     * @return response with data about successfully created customer
     **/
    @Transactional
    public RegisterResponse createCustomer(RegisterRequest inputCustomer) {
        try{

            Optional<Customer> customerFromDb = repository.findByEmail(inputCustomer.getEmail());

            if (customerFromDb.isPresent()){
                throw new CustomerException(HttpStatus.BAD_REQUEST,
                        "Customer with " + inputCustomer.getEmail() + " already exists");
            }


            Customer customerForSave = Customer.builder()
                    .firstName(inputCustomer.getFirstName())
                    .lastName(inputCustomer.getLastName())
                    .email(inputCustomer.getEmail())
                    .birthDate(inputCustomer.getBirthDate())
                    .build();

            customerForSave.setPassword(passwordEncoder.encode(inputCustomer.getPassword()));

            Customer savedCustomer = repository.save(customerForSave);

            return RegisterResponse.builder()
                    .id(savedCustomer.getId())
                    .firstName(savedCustomer.getFirstName())
                    .lastName(savedCustomer.getLastName())
                    .email(savedCustomer.getEmail())
                    .birthDate(savedCustomer.getBirthDate())
                    .build();

        }catch (Exception e) {
            throw new CustomerException(HttpStatus.BAD_REQUEST, "Couldn't create customer", e);
        }
    }

    /**
     * Method for getting customer from DB by id
     * @exception CustomerException if something went wrong in getting customer from DB
     * @param id of customer which we want to get
     * @return response with data about customer
     **/
    public CustomerReadDto getCustomer(Long id) {
        try {
            Optional<Customer> maybeCustomer = repository.findById(id);

            if (maybeCustomer.isEmpty()){
                throw new CustomerException(HttpStatus.BAD_REQUEST, "Customer with with id: " + id + " not exists");
            }

            Customer customer = maybeCustomer.get();

            return CustomerReadDto.builder()
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .email(customer.getEmail())
                    .birthDate(customer.getBirthDate())
                    .build();

        }catch (Exception e) {
            throw new CustomerException(HttpStatus.BAD_REQUEST, "Couldn't get customer", e);
        }
    }

    /**
     * Method for updating customer in DB by id
     * @exception CustomerException if something went wrong in updating customer
     * @param id of customer which we want to update
     * @param customerInfo data for updating
     * @return response with data about updated customer
     **/
    @Transactional
    public CustomerReadDto updateCustomer(Long id, CustomerReadDto customerInfo) {
        try {
            Optional<Customer> maybeCustomer = repository.findById(id);

            if (maybeCustomer.isEmpty()){
                throw new CustomerException(HttpStatus.BAD_REQUEST,
                        "Customer with id: " + id + " doesn't exists");
            }

            Customer customer = maybeCustomer.get();

            customer.setFirstName(customerInfo.getFirstName());
            customer.setLastName(customerInfo.getLastName());
            customer.setEmail(customerInfo.getEmail());
            customer.setBirthDate(customerInfo.getBirthDate());

            return CustomerReadDto.builder()
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .email(customer.getEmail())
                    .birthDate(customer.getBirthDate())
                    .build();

        }catch (Exception e) {
            throw new CustomerException(HttpStatus.BAD_REQUEST, "Could not update customer", e);
        }
    }

    /**
     * Method for deleting customer from DB by id
     * @exception CustomerException if something went wrong in deleting customer from DB
     * @param id of customer which we want to delete
     * @return response boolean which shows status of deletion
     **/
    @Transactional
    public boolean deleteCustomer(Long id) {
        try {
            Optional<Customer> maybeCustomer = repository.findById(id);

            if (maybeCustomer.isEmpty()){
                throw new CustomerException(HttpStatus.BAD_REQUEST,
                        "Customer with id: " + id + " doesn't exists");
            }

            repository.delete(maybeCustomer.get());

            return true;
        }catch (Exception e) {
            throw new CustomerException(HttpStatus.BAD_REQUEST, "Could not delete customer", e);
        }

    }
}
