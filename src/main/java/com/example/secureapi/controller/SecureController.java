package com.example.secureapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SecureController {

    @GetMapping("/secure")
    public Map<String, String> secureEndpoint() {
        return Map.of("message", "You have accessed a protected endpoint!");
    }
}

