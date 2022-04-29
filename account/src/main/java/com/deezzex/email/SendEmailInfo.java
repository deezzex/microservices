package com.deezzex.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class, DTO for sending email.
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailInfo {
    String emailTo;
    String subject;
    String body;
}
