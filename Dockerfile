# Spring Boot REST API Dockerfile
#
# Enterprise-grade REST API with Spring Boot framework
# Features JWT authentication, database integration, caching, and comprehensive API documentation
#
# @author RSK World
# @author Molla Samser (Founder)
# @author Rima Khatun (Designer & Tester)
# @website https://rskworld.in
# @contact help@rskworld.in, support@rskworld.in
# @phone +91 93305 39277
# @address Nutanhat, Mongolkote, Purba Burdwan, West Bengal, India, 713147
# @year 2026
#
# This project is part of RSK World's free programming resources and source code collection.
# Content used for educational purposes only. View Disclaimer: https://rskworld.in/disclaimer.php

# Use Maven image for building
FROM maven:3.9-openjdk-17 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use OpenJDK 17 for runtime
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR file
COPY --from=builder /app/target/spring-boot-api-1.0.0.jar app.jar

# Create non-root user
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/api/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
