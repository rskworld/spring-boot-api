# Spring Boot REST API

Enterprise-grade REST API with Spring Boot framework. Features JWT authentication, database integration, caching, and comprehensive API documentation.

## 🚀 Features

- **Enterprise-grade API** - Production-ready REST API architecture
- **JWT Authentication** - Secure token-based authentication system
- **Database Integration** - PostgreSQL with JPA/Hibernate
- **Caching System** - Redis caching for improved performance
- **API Documentation** - Swagger/OpenAPI 3.0 documentation
- **Microservices Architecture** - Scalable and modular design
- **Spring Framework** - Latest Spring Boot 3.x with Java 17

## 🛠️ Technologies Used

- **Java 17** - Modern Java with latest features
- **Spring Boot 3.2.0** - Enterprise application framework
- **Spring Security** - Authentication and authorization
- **PostgreSQL** - Relational database
- **Redis** - In-memory caching
- **JWT** - JSON Web Tokens for authentication
- **Swagger/OpenAPI** - API documentation
- **Maven** - Build and dependency management

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher
- Redis 6 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/rskworld/spring-boot-api.git
cd spring-boot-api
```

### 2. Database Setup

Create a PostgreSQL database:

```sql
CREATE DATABASE spring_boot_api;
CREATE USER postgres WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE spring_boot_api TO postgres;
```

### 3. Redis Setup

Install and start Redis server:

```bash
# On Ubuntu/Debian
sudo apt-get install redis-server
sudo systemctl start redis-server

# On macOS with Homebrew
brew install redis
brew services start redis

# On Windows
# Download Redis from https://github.com/microsoftarchive/redis/releases
```

### 4. Configuration

Update `src/main/resources/application.properties` with your database and Redis credentials:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/spring_boot_api
spring.datasource.username=postgres
spring.datasource.password=your_password

# Redis Configuration
spring.redis.host=localhost
spring.redis.port=6379
```

### 5. Run the Application

```bash
mvn clean install
mvn spring-boot:run
```

The application will start at `http://localhost:8080`

## 📚 API Documentation

Once the application is running, you can access:

- **Swagger UI**: `http://localhost:8080/api/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/api/api-docs`

## 🔐 Authentication

### Register a New User

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe"
  }'
```

### Login and Get JWT Token

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "usernameOrEmail": "john_doe",
    "password": "password123"
  }'
```

### Use JWT Token for Protected Endpoints

```bash
curl -X GET http://localhost:8080/api/products \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🏗️ Project Structure

```
src/main/java/com/rskworld/
├── SpringBootApiApplication.java          # Main application class
├── config/
│   ├── SecurityConfig.java                 # Security configuration
│   ├── SwaggerConfig.java                  # Swagger documentation
│   ├── RedisConfig.java                    # Redis caching config
│   └── JpaConfig.java                      # JPA configuration
├── controller/
│   ├── AuthController.java                 # Authentication endpoints
│   └── ProductController.java               # Product management
├── entity/
│   ├── User.java                           # User entity
│   ├── Role.java                           # Role entity
│   └── Product.java                        # Product entity
├── repository/
│   ├── UserRepository.java                 # User repository
│   ├── RoleRepository.java                 # Role repository
│   └── ProductRepository.java               # Product repository
├── service/
│   ├── UserService.java                     # User service
│   └── ProductService.java                 # Product service
├── security/
│   ├── JwtTokenUtil.java                   # JWT utility
│   ├── JwtAuthenticationFilter.java        # JWT filter
│   └── JwtAuthenticationEntryPoint.java   # JWT entry point
├── dto/
│   ├── JwtResponse.java                    # JWT response DTO
│   ├── LoginRequest.java                   # Login request DTO
│   ├── SignUpRequest.java                  # Sign up request DTO
│   └── RefreshTokenRequest.java            # Refresh token DTO
└── exception/
    ├── GlobalExceptionHandler.java         # Global exception handler
    ├── ResourceNotFoundException.java      # Resource not found exception
    ├── ErrorResponse.java                  # Error response DTO
    └── ValidationErrorResponse.java        # Validation error DTO
```

## 🔧 API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | User login |
| POST | `/api/auth/refresh` | Refresh JWT token |

### Product Management

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/products` | Get all active products | No |
| GET | `/api/products/page` | Get paginated products | No |
| GET | `/api/products/{id}` | Get product by ID | No |
| GET | `/api/products/sku/{sku}` | Get product by SKU | No |
| GET | `/api/products/category/{category}` | Get products by category | No |
| GET | `/api/products/brand/{brand}` | Get products by brand | No |
| GET | `/api/products/search` | Search products | No |
| GET | `/api/products/price-range` | Get products by price range | No |
| GET | `/api/products/latest` | Get latest products | No |
| POST | `/api/products` | Create product | Yes (Admin) |
| PUT | `/api/products/{id}` | Update product | Yes (Admin) |
| DELETE | `/api/products/{id}` | Delete product (soft) | Yes (Admin) |

## 🧪 Testing

### Run Unit Tests

```bash
mvn test
```

### Run Integration Tests

```bash
mvn test -P integration-test
```

### Generate Test Coverage Report

```bash
mvn jacoco:report
```

## 📊 Monitoring and Logging

The application includes comprehensive logging and monitoring:

- **Application Logs**: Console and file logging
- **Security Logs**: Authentication and authorization events
- **Performance Metrics**: Request timing and cache hit rates
- **Error Tracking**: Comprehensive exception handling

## 🚀 Deployment

### Docker Deployment

```bash
# Build Docker image
docker build -t spring-boot-api .

# Run with Docker Compose
docker-compose up -d
```

### Production Deployment

1. Update production configuration in `application-prod.properties`
2. Build the application: `mvn clean package`
3. Deploy the JAR file to your server
4. Run with: `java -jar spring-boot-api-1.0.0.jar`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is part of RSK World's free programming resources and source code collection.

**⚠️ Disclaimer**: Content used for educational purposes only. View full disclaimer at: https://rskworld.in/disclaimer.php

## 👥 Team

- **Molla Samser** - Founder & Lead Developer
- **Rima Khatun** - Designer & Tester

## 📞 Contact

- **Website**: https://rskworld.in
- **Email**: help@rskworld.in, support@rskworld.in
- **Phone**: +91 93305 39277
- **Address**: Nutanhat, Mongolkote, Purba Burdwan, West Bengal, India, 713147

## 🌐 More Resources

- **Game Development**: https://rskworld.in/games
- **Web Development**: https://rskworld.in/web
- **Mobile Development**: https://rskworld.in/mobile
- **AI Development**: https://rskworld.in/ai
- **Development Tools**: https://rskworld.in/dev-tools

---

© 2026 RSK World. All rights reserved.
