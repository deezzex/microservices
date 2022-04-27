package com.deezzex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * POJO class, DTO for receiving JSON object from request body on creation new customer.
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String password;
}
