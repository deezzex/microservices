package com.deezzex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerReadDto {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
}
