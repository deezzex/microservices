package com.deezzex.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Custom exception handler class for customer exceptions, extended from ResponseEntityExceptionHandler.
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@Slf4j
@ControllerAdvice
public class LoginExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Method for handling LoginException and returning appropriate response
     * @param ex caused exception
     * @param request web request
     * @return Response entity with appropriate status code and body as a message from exception
     **/
    @ExceptionHandler(value = {LoginException.class})
    public ResponseEntity<Object> handleCustomerException(LoginException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();

        log.error(bodyOfResponse);

        LoginException customerException = new LoginException(ex.getHttpStatus(), bodyOfResponse);

        return new ResponseEntity<>(customerException.getMessage(), new HttpHeaders(), customerException.getHttpStatus());
    }
}
