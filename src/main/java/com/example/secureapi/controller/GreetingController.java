package com.example.secureapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @GetMapping("/greet")
    public Greeting greet(@RequestParam(defaultValue = "world") String name) {
        return new Greeting("Hello, " + name + "!");
    }

    public record Greeting(String message) {}
}

