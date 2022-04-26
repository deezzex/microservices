package com.deezzex.customer;

import com.deezzex.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class, which contains account and related customer, class for responding data
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountWithCustomer {
    private Customer customer;
    private Account account;
}
