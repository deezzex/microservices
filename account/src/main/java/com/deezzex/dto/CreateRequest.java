package com.deezzex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class, DTO for receiving JSON object from request body on creation new account.
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRequest {
    private String name;
    private Double amount;
    private Long customerId;
}
