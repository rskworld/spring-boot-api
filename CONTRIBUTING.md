# Contributing to Spring Boot REST API

Thank you for your interest in contributing to the Spring Boot REST API project! This guide will help you get started with contributing to this RSK World project.

## 🤝 How to Contribute

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher (for production testing)
- Redis 6 or higher
- Git
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Getting Started

1. **Fork the Repository**
   ```bash
   git clone https://github.com/your-username/spring-boot-api.git
   cd spring-boot-api
   ```

2. **Set Up Development Environment**
   ```bash
   # Install dependencies
   mvn clean install
   
   # Run the application
   mvn spring-boot:run
   ```

3. **Create a Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

### Development Guidelines

#### Code Style
- Follow Java naming conventions
- Use meaningful variable and method names
- Add Javadoc comments for public methods
- Keep methods small and focused
- Use proper exception handling

#### Commit Messages
- Use clear and descriptive commit messages
- Format: `type(scope): description`
  - `feat(auth): add JWT refresh token support`
  - `fix(products): resolve pagination issue`
  - `docs(readme): update API documentation`

#### Testing
- Write unit tests for new features
- Ensure all tests pass before submitting
- Use descriptive test names
- Test both positive and negative scenarios

### Project Structure

```
src/main/java/com/rskworld/
├── SpringBootApiApplication.java     # Main application class
├── config/                           # Configuration classes
│   ├── SecurityConfig.java          # Security configuration
│   ├── SwaggerConfig.java           # API documentation
│   ├── RedisConfig.java             # Caching configuration
│   └── JpaConfig.java               # JPA configuration
├── controller/                       # REST controllers
│   ├── AuthController.java          # Authentication endpoints
│   ├── ProductController.java       # Product management
│   └── HomeController.java          # Home and health endpoints
├── entity/                          # JPA entities
│   ├── User.java                    # User entity
│   ├── Role.java                    # Role entity
│   └── Product.java                 # Product entity
├── repository/                      # Data repositories
│   ├── UserRepository.java          # User repository
│   ├── RoleRepository.java          # Role repository
│   └── ProductRepository.java       # Product repository
├── service/                         # Business logic
│   ├── UserService.java              # User service
│   └── ProductService.java          # Product service
├── security/                        # Security components
│   ├── JwtTokenUtil.java            # JWT utilities
│   ├── JwtAuthenticationFilter.java # JWT filter
│   └── JwtAuthenticationEntryPoint.java # JWT entry point
├── dto/                            # Data transfer objects
│   ├── JwtResponse.java             # JWT response
│   ├── LoginRequest.java            # Login request
│   ├── SignUpRequest.java           # Registration request
│   └── RefreshTokenRequest.java     # Token refresh request
└── exception/                       # Exception handling
    ├── GlobalExceptionHandler.java   # Global exception handler
    ├── ResourceNotFoundException.java # 404 exception
    ├── ErrorResponse.java           # Error response DTO
    └── ValidationErrorResponse.java  # Validation error DTO
```

### Coding Standards

#### Java Code Style
- Use 4 spaces for indentation
- Maximum line length: 120 characters
- Place opening braces on the same line
- Use meaningful class and method names
- Add proper Javadoc documentation

#### Example Code Style
```java
/**
 * Service for managing user operations.
 * 
 * @author Your Name
 * @since 1.0.0
 */
@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    /**
     * Creates a new user with the provided details.
     * 
     * @param user the user to create
     * @return the created user
     * @throws IllegalArgumentException if user data is invalid
     */
    public User createUser(User user) {
        validateUser(user);
        return userRepository.save(user);
    }
    
    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
    }
}
```

### Pull Request Process

1. **Update Documentation**
   - Update README.md if needed
   - Add/update API documentation
   - Update CHANGELOG.md

2. **Run Tests**
   ```bash
   mvn test
   mvn verify
   ```

3. **Submit Pull Request**
   - Provide clear description of changes
   - Link to related issues
   - Include screenshots if applicable
   - Ensure CI/CD passes

### Types of Contributions

#### 🐛 Bug Fixes
- Identify and fix bugs
- Add tests to prevent regression
- Update documentation

#### ✨ New Features
- Implement new functionality
- Follow existing patterns
- Add comprehensive tests

#### 📚 Documentation
- Improve API documentation
- Add code comments
- Update README and guides

#### 🎨 Improvements
- Code refactoring
- Performance optimizations
- Security enhancements

### Review Process

1. **Code Review**
   - All PRs require review
   - Focus on code quality and functionality
   - Ensure tests are comprehensive

2. **Testing**
   - Automated tests must pass
   - Manual testing for UI changes
   - Performance testing for critical changes

3. **Merge**
   - Maintainer approval required
   - Squash and merge commits
   - Update version if necessary

### Community Guidelines

#### Be Respectful
- Treat all contributors with respect
- Provide constructive feedback
- Welcome newcomers

#### Communication
- Use clear and professional language
- Ask questions if unsure
- Share knowledge and help others

#### Quality Standards
- Maintain high code quality
- Follow established patterns
- Test thoroughly

## 📞 Get Help

- **Issues**: [GitHub Issues](https://github.com/rskworld/spring-boot-api/issues)
- **Discussions**: [GitHub Discussions](https://github.com/rskworld/spring-boot-api/discussions)
- **Email**: help@rskworld.in
- **Website**: https://rskworld.in

## 🏆 Recognition

Contributors will be recognized in:
- README.md contributors section
- Release notes
- RSK World website
- Social media mentions

---

Thank you for contributing to RSK World's Spring Boot REST API project! 🎉

## Contact Information

**RSK World**
- **Website**: https://rskworld.in
- **Email**: help@rskworld.in, support@rskworld.in
- **Phone**: +91 93305 39277
- **Address**: Nutanhat, Mongolkote, Purba Burdwan, West Bengal, India, 713147

---

© 2026 RSK World. All rights reserved.
