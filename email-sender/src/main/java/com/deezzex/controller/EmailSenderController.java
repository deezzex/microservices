package com.deezzex.controller;

import com.deezzex.entity.SendEmailInfo;
import com.deezzex.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for email sender REST endpoints.
 * This class handles the CRUD operations for email sender, via HTTP actions.
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mails")
public class EmailSenderController {

    private final EmailSenderService senderService;

    /**
     * POST endpoint for sending email to customer
     * @param emailInfo metadata for email sending
     * @return response entity with OK HTTP status and email address on which was sent letter
     **/
    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody SendEmailInfo emailInfo){
        senderService.sendEmail(emailInfo.getEmailTo(), emailInfo.getSubject(), emailInfo.getBody());

        log.info("Send email to {}", emailInfo.getEmailTo());

        return new ResponseEntity<>(emailInfo.getEmailTo(), HttpStatus.OK);
    }
}
