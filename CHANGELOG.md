# Changelog

All notable changes to the Spring Boot REST API project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-01-29

### Added
- 🚀 Initial release of Spring Boot REST API
- 🔐 JWT Authentication system with token management
- 🗄️ Database integration with H2 (in-memory) and PostgreSQL support
- ⚡ Redis caching for improved performance
- 📚 Comprehensive API documentation with Swagger/OpenAPI 3.0
- 👥 User management with role-based access control
- 📦 Product management system with CRUD operations
- 🛡️ Security configuration with Spring Security
- 🏗️ Enterprise-grade architecture with microservices design
- 📝 Complete RSK World branding and contact information
- 🐳 Docker support with docker-compose configuration
- 🧪 H2 Console for database management
- 📊 Health check endpoints for monitoring
- 🔄 Refresh token support for extended sessions
- 📋 Comprehensive error handling and validation
- 🎯 RESTful API design following best practices

### Features
- **Authentication & Authorization**
  - User registration and login
  - JWT token-based authentication
  - Role-based access control (ADMIN, USER, MANAGER)
  - Token refresh mechanism
  
- **Product Management**
  - CRUD operations for products
  - Search and filtering capabilities
  - Pagination support
  - Price range filtering
  - Category and brand-based filtering
  - Low stock alerts
  
- **Caching**
  - Redis integration for performance
  - Cacheable repository methods
  - Configurable cache TTL
  
- **Documentation**
  - Swagger UI integration
  - OpenAPI 3.0 specification
  - Interactive API testing
  
- **Database**
  - JPA/Hibernate integration
  - Entity relationships
  - Database migrations
  - H2 console for development
  
- **Security**
  - Password encryption with BCrypt
  - CORS configuration
  - CSRF protection
  - Session management

### Technical Stack
- **Backend**: Java 17, Spring Boot 3.2.0
- **Security**: Spring Security, JWT
- **Database**: H2, PostgreSQL, JPA/Hibernate
- **Cache**: Redis
- **Documentation**: Swagger/OpenAPI 3.0
- **Build**: Maven
- **Containerization**: Docker

### API Endpoints
- **Authentication**: `/auth/login`, `/auth/register`, `/auth/refresh`
- **Products**: `/products` (GET, POST, PUT, DELETE)
- **Health**: `/health`
- **Documentation**: `/swagger-ui.html`
- **Database**: `/h2-console`

---

## Development Team

- **Molla Samser** - Founder & Lead Developer
- **Rima Khatun** - Designer & Tester

## Contact

- **Website**: https://rskworld.in
- **Email**: help@rskworld.in, support@rskworld.in
- **Phone**: +91 93305 39277
- **Address**: Nutanhat, Mongolkote, Purba Burdwan, West Bengal, India, 713147

---

© 2026 RSK World. All rights reserved.
