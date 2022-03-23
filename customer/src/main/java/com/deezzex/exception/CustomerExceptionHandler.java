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
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomerException.class})
    public ResponseEntity<Object> handleCustomerException(CustomerException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();

        log.error(bodyOfResponse);

        CustomerException customerException = new CustomerException(ex.getHttpStatus(), bodyOfResponse);

        return new ResponseEntity<>(customerException.getMessage(), new HttpHeaders(), customerException.getHttpStatus());
    }
}
