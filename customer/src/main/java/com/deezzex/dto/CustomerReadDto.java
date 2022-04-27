package com.deezzex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * POJO class, DTO for receiving JSON object of customer.
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReadDto {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
}
