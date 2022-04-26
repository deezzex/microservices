package com.deezzex.repository;

import com.deezzex.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Account repository for interacting with database, extended from JpaRepository
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
