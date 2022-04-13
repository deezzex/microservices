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
public class AccountExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {AccountException.class})
    public ResponseEntity<Object> handleCustomerException(AccountException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();

        log.error(bodyOfResponse);

        AccountException accountException = new AccountException(ex.getHttpStatus(), bodyOfResponse);

        return new ResponseEntity<>(accountException.getMessage(), new HttpHeaders(), accountException.getHttpStatus());
    }
}
