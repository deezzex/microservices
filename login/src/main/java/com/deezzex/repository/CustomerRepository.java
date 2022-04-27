package com.deezzex.repository;

import com.deezzex.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Customer repository for interacting with database, extended from JpaRepository
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
