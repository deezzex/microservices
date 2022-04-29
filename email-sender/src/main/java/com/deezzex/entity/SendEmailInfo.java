package com.deezzex.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class metadata for sending email.
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
