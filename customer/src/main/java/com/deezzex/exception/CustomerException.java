package com.deezzex.exception;

import org.springframework.http.HttpStatus;

public class CustomerException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;

    public CustomerException(HttpStatus status, String message) {
        super();
        this.httpStatus = status;
        this.message = message;
    }

    public CustomerException(HttpStatus status, String message, Exception cause) {
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
