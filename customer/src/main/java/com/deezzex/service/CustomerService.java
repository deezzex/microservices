package com.deezzex.service;

import com.deezzex.dto.CustomerReadDto;
import com.deezzex.dto.RegisterRequest;
import com.deezzex.dto.RegisterResponse;
import com.deezzex.entity.Customer;
import com.deezzex.exception.CustomerException;
import com.deezzex.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerRepository repository;

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
            throw new CustomerException(HttpStatus.BAD_REQUEST, "Could not update customer");
        }
    }

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
            throw new CustomerException(HttpStatus.BAD_REQUEST, "Could not delete customer");
        }

    }
}
