package com.rskworld;

/**
 * Spring Boot REST API Application
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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApiApplication.class, args);
        System.out.println("\n=================================");
        System.out.println("Spring Boot REST API Started!");
        System.out.println("Developed by RSK World Team");
        System.out.println("Website: https://rskworld.in");
        System.out.println("Contact: help@rskworld.in");
        System.out.println("=================================");
    }
}
