# Troubleshooting Guide

## 🚨 Quick Fixes

### Application Won't Start
```bash
# Check Java version
java -version

# Check port availability
netstat -an | grep 8080

# Kill process on port 8080
sudo kill -9 $(lsof -ti:8080)
```

### Database Issues
```bash
# Check PostgreSQL
sudo systemctl status postgresql

# Start PostgreSQL
sudo systemctl start postgresql

# Test connection
psql -h localhost -U postgres
```

### Redis Issues
```bash
# Check Redis
sudo systemctl status redis

# Start Redis
sudo systemctl start redis

# Test Redis
redis-cli ping
```

### 404 Errors
1. Verify URL: `http://localhost:8080/`
2. Check application logs
3. Verify controller mappings

### Authentication Issues
1. Check JWT token expiration
2. Verify user credentials
3. Check user roles

## 🔧 Common Solutions

### Port Conflicts
```properties
# Change port in application.properties
server.port=8081
```

### Database Connection
```properties
# Verify database URL
spring.datasource.url=jdbc:postgresql://localhost:5432/spring_boot_api
spring.datasource.username=postgres
spring.datasource.password=password
```

### Enable Debug Logging
```properties
logging.level.com.rskworld=DEBUG
logging.level.org.springframework.security=DEBUG
```

## 📞 Support
- **Email**: help@rskworld.in
- **Website**: https://rskworld.in
- **Phone**: +91 93305 39277

---

© 2026 RSK World. All rights reserved.
