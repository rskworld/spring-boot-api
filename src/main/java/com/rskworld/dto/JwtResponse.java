package com.rskworld.dto;

/**
 * JWT Response DTO
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtResponse {

    private String token;
    private String refreshToken;
    private String username;
    private Collection<?> authorities;

    public JwtResponse(String token, String refreshToken, String username, Collection<?> authorities) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.username = username;
        this.authorities = authorities;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Collection<?> getAuthorities() { return authorities; }
    public void setAuthorities(Collection<?> authorities) { this.authorities = authorities; }
}
