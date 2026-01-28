package com.rskworld.controller;

/**
 * Home Controller
 * 
 * Enterprise-grade REST API with Spring Boot framework
 * Features JWT authentication, database integration, caching, and comprehensive API documentation
 * 
 * @author RSK World
 * @author Molla Samser (Founder)
 * @author Rima Khatun (Designer & Tester)
 * @website https://rskworld.in
 * @contact help@rskworld.in, support@rskworld.in
 * @phone +91 93305 39277
 * @address Nutanhat, Mongolkote, Purba Burdwan, West Bengal, India, 713147
 * @year 2026
 * 
 * This project is part of RSK World's free programming resources and source code collection.
 * Content used for educational purposes only. View Disclaimer: https://rskworld.in/disclaimer.php
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Spring Boot REST API is running!");
        response.put("version", "1.0.0");
        response.put("status", "ACTIVE");
        response.put("documentation", "/swagger-ui.html");
        response.put("h2-console", "/h2-console");
        response.put("team", "RSK World");
        response.put("website", "https://rskworld.in");
        response.put("contact", "help@rskworld.in");
        response.put("year", "2026");
        return response;
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("application", "Spring Boot REST API");
        response.put("version", "1.0.0");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
}
