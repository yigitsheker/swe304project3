package com.example.swe304project3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/api/status")
    public String status() {
        return "SWE304 Project 3 - School Management API is running!";
    }

    @GetMapping("/api/version")
    public String version() {
        return "Version 1.0 - Initial deployment";
    }
}