package com.deezzex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for fallback REST endpoints.
 * @version 1
 * @author Sviatoslav Pshtir
 **/

@RestController
public class FallbackController {

    @GetMapping("/customerFallback")
    public String customerFallback() {
        return "Oops... Customer service is unavailable. Please try again later.";
    }

    @GetMapping("/accountFallback")
    public String accountFallback() {
        return "Oops... Account service is unavailable. Please try again later.";
    }

    @GetMapping("/emailFallback")
    public String loginFallback() {
        return "Oops... Email service is unavailable. Please try again later.";
    }

}
