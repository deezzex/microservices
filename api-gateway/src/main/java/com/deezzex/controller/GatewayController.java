package com.deezzex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/customerFallback")
    public String customerFallback() {
        return "Oops... Customer service is unavailable. Please try again later.";
    }

    @GetMapping("/accountFallback")
    public String accountFallback() {
        return "Oops... Account service is unavailable. Please try again later.";
    }

}
