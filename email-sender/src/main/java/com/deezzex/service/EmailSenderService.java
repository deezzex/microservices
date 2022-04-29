package com.deezzex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service layer class for mail sender application
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    /**
     * Method for sending email to customer with some information
     * @param emailTo customer's email address
     * @param subject of email
     * @param body text of email
     **/
    public void sendEmail(String emailTo, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(emailTo);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }

}
