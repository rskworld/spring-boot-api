# Security Guide

This document outlines the security features and best practices for the Spring Boot REST API.

## 🔐 Security Features

### Authentication & Authorization
- **JWT Token-based Authentication**
- **Role-based Access Control (RBAC)**
- **Password Encryption with BCrypt**
- **Session Management**
- **Token Refresh Mechanism**

### Data Protection
- **Input Validation**
- **SQL Injection Prevention**
- **XSS Protection**
- **CSRF Protection**
- **CORS Configuration**

### API Security
- **Rate Limiting**
- **Request Validation**
- **Error Handling**
- **Security Headers**
- **HTTPS Enforcement**

## 🛡️ Security Configuration

### JWT Configuration
```properties
# JWT Settings
jwt.secret=mySecretKey123456789012345678901234567890
jwt.expiration=86400000
jwt.refresh-token.expiration=604800000
```

### Security Headers
```java
@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .headers(headers -> headers
                .frameOptions().deny()
                .contentTypeOptions().and()
                .httpStrictTransportSecurity(hstsConfig -> hstsConfig
                    .includeSubDomains(true)
                    .maxAgeInSeconds(31536000))
                .and()
                .cacheControl()
            );
    }
}
```

### Password Policy
```java
@Service
public class UserService {
    
    private static final String PASSWORD_PATTERN = 
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    
    public void validatePassword(String password) {
        if (!password.matches(PASSWORD_PATTERN)) {
            throw new IllegalArgumentException(
                "Password must be at least 8 characters long, " +
                "contain uppercase, lowercase, digit, and special character"
            );
        }
    }
}
```

## 🔒 Best Practices

### 1. Environment Variables
```bash
# Use environment variables for sensitive data
export JWT_SECRET=your-super-secret-key
export DB_PASSWORD=your-database-password
export REDIS_PASSWORD=your-redis-password
```

### 2. Database Security
```sql
-- Create dedicated user for application
CREATE USER api_user WITH PASSWORD 'secure_password';
GRANT CONNECT ON DATABASE spring_boot_api TO api_user;
GRANT USAGE ON SCHEMA public TO api_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO api_user;
```

### 3. Redis Security
```bash
# Enable Redis authentication
requirepass your-redis-password

# Disable dangerous commands
rename-command FLUSHDB ""
rename-command FLUSHALL ""
rename-command KEYS ""
```

### 4. API Rate Limiting
```java
@RestController
@RequestMapping("/api")
public class RateLimitController {
    
    private final RateLimiter rateLimiter = RateLimiter.create(100.0); // 100 requests per second
    
    @GetMapping("/protected")
    public ResponseEntity<?> protectedEndpoint() {
        if (!rateLimiter.tryAcquire()) {
            return ResponseEntity.status(429).body("Too many requests");
        }
        return ResponseEntity.ok("Success");
    }
}
```

## 🚨 Security Checklist

### Development Environment
- [ ] Use strong passwords
- [ ] Enable debug logging only in development
- [ ] Use H2 database for development
- [ ] Disable production security features

### Production Environment
- [ ] Use HTTPS/TLS
- [ ] Enable security headers
- [ ] Use PostgreSQL/MySQL
- [ ] Configure firewall rules
- [ ] Enable audit logging
- [ ] Regular security updates

### Code Security
- [ ] Input validation
- [ ] Output encoding
- [ ] Error handling
- [ ] Authentication checks
- [ ] Authorization checks

## 🔍 Security Testing

### Authentication Testing
```bash
# Test login with invalid credentials
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"usernameOrEmail":"invalid","password":"wrong"}'

# Test without token
curl -X GET http://localhost:8080/products

# Test with expired token
curl -X GET http://localhost:8080/products \
  -H "Authorization: Bearer expired-token"
```

### Input Validation Testing
```bash
# Test SQL injection
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"admin'; DROP TABLE users; --","password":"password"}'

# Test XSS
curl -X POST http://localhost:8080/products \
  -H "Authorization: Bearer token" \
  -H "Content-Type: application/json" \
  -d '{"name":"<script>alert('xss')</script>"}'
```

## 📊 Security Monitoring

### Logging
```properties
# Security logging
logging.level.org.springframework.security=INFO
logging.level.com.rskworld.security=DEBUG

# Audit logging
logging.level.org.springframework.security.web.access=DEBUG
```

### Monitoring Endpoints
```java
@RestController
@RequestMapping("/admin")
public class SecurityController {
    
    @GetMapping("/security-info")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> getSecurityInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("activeUsers", getActiveUserCount());
        info.put("failedAttempts", getFailedLoginAttempts());
        info.put("lastSecurityUpdate", getLastSecurityUpdate());
        return info;
    }
}
```

## 🔧 Security Tools

### OWASP ZAP
```bash
# Run security scan
docker run -t owasp/zap2docker-stable zap-baseline.py -t http://localhost:8080
```

### Security Headers Check
```bash
# Check security headers
curl -I http://localhost:8080/
```

### SSL/TLS Configuration
```bash
# Generate SSL certificate
keytool -genkeypair -alias spring-boot-api -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 365

# Configure HTTPS
server.ssl.enabled=true
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=password
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=spring-boot-api
```

## 🚨 Incident Response

### Security Incident Steps
1. **Identify** the breach
2. **Contain** the damage
3. **Investigate** the cause
4. **Remediate** vulnerabilities
5. **Document** the incident
6. **Review** security measures

### Emergency Contacts
- **Security Team**: security@rskworld.in
- **Legal Team**: legal@rskworld.in
- **Management**: admin@rskworld.in

## 📚 Security Resources

### Documentation
- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [JWT Best Practices](https://auth0.com/blog/jwt-best-practices/)

### Tools
- **OWASP ZAP** - Security scanning
- **Burp Suite** - Web application testing
- **SonarQube** - Code security analysis
- **Fail2Ban** - Intrusion prevention

## 📞 Security Support

For security concerns:
- **Email**: security@rskworld.in
- **Website**: https://rskworld.in
- **Phone**: +91 93305 39277

---

© 2026 RSK World. All rights reserved.
