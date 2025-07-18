package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirebaseController 
{

    public FirebaseController() {
        // Constructor
    }

    @GetMapping("/getUserDetails")
    public String getUserDetails(@RequestParam String name) {
        // Just a placeholder response to test
        return "User details for: " + name;
    }
}
