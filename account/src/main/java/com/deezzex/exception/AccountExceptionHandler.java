package com.deezzex.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Custom exception handler class for account exceptions, extended from ResponseEntityExceptionHandler.
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Slf4j
@ControllerAdvice
public class AccountExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Method for handling AccountException and returning appropriate response
     * @param ex caused exception
     * @param request web request
     * @return Response entity with appropriate status code and body as a message from exception
     **/
    @ExceptionHandler(value = {AccountException.class})
    public ResponseEntity<Object> handleCustomerException(AccountException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();

        log.error(bodyOfResponse);

        AccountException accountException = new AccountException(ex.getHttpStatus(), bodyOfResponse);

        return new ResponseEntity<>(accountException.getMessage(), new HttpHeaders(), accountException.getHttpStatus());
    }
}
