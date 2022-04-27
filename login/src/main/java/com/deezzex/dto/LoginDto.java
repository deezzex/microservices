package com.deezzex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class, DTO for receiving JSON object of customer for login.
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private Long id;
    private String username;
    private String password;
    private String role;
    private boolean isExist;
}
