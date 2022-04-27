package com.deezzex.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class for login service, extended from RuntimeException and modified
 * @version 1
 * @author Sviatoslav Pshtir
 **/

public class LoginException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;

    public LoginException(HttpStatus status, String message) {
        super();
        this.httpStatus = status;
        this.message = message;
    }

    public LoginException(HttpStatus status, String message, Exception cause) {
        super();
        this.httpStatus = status;
        this.message = message + " >>>>>> " + cause.getMessage();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
