package com.deezzex.customer;

import com.deezzex.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTemplate {
    private Customer customer;
    private Account account;
}
