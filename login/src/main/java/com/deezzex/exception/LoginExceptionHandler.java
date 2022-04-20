package com.deezzex.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class LoginExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {LoginException.class})
    public ResponseEntity<Object> handleCustomerException(LoginException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();

        log.error(bodyOfResponse);

        LoginException customerException = new LoginException(ex.getHttpStatus(), bodyOfResponse);

        return new ResponseEntity<>(customerException.getMessage(), new HttpHeaders(), customerException.getHttpStatus());
    }
}
