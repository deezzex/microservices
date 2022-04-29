package com.deezzex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class, DTO for sending response.
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadAccountDto {
    private String name;
    private Double amount;
    private Long customerId;
}
