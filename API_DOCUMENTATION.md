# Spring Boot REST API Documentation

This document provides comprehensive information about the Spring Boot REST API endpoints, authentication, and usage examples.

## 🌐 Base URL

```
http://localhost:8080
```

## 🔐 Authentication

The API uses JWT (JSON Web Token) for authentication. Most endpoints require a valid JWT token in the Authorization header.

### Authentication Flow

1. **Register User**: Create a new user account
2. **Login**: Authenticate and receive JWT token
3. **Use Token**: Include token in subsequent requests

### Request Headers

```http
Authorization: Bearer <your-jwt-token>
Content-Type: application/json
```

## 📋 API Endpoints

### Authentication Endpoints

#### Register User
```http
POST /auth/register
```

**Request Body:**
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe",
  "phone": "+1234567890"
}
```

**Response:**
```json
{
  "message": "User registered successfully!"
}
```

#### Login
```http
POST /auth/login
```

**Request Body:**
```json
{
  "usernameOrEmail": "john_doe",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "username": "john_doe",
  "authorities": ["ROLE_USER"]
}
```

#### Refresh Token
```http
POST /auth/refresh
```

**Request Body:**
```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Response:**
```json
{
  "token": "new-jwt-token",
  "refreshToken": "new-refresh-token",
  "username": "john_doe",
  "authorities": ["ROLE_USER"]
}
```

### Product Endpoints

#### Get All Products
```http
GET /products
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Laptop Pro 15",
    "description": "High-performance laptop with 16GB RAM",
    "price": 1299.99,
    "quantity": 50,
    "sku": "LP-15-001",
    "category": "Electronics",
    "brand": "TechBrand",
    "imageUrl": null,
    "active": true,
    "createdAt": "2026-01-29T00:00:00",
    "updatedAt": "2026-01-29T00:00:00"
  }
]
```

#### Get Product by ID
```http
GET /products/{id}
```

**Path Parameters:**
- `id` (Long): Product ID

**Response:**
```json
{
  "id": 1,
  "name": "Laptop Pro 15",
  "description": "High-performance laptop with 16GB RAM",
  "price": 1299.99,
  "quantity": 50,
  "sku": "LP-15-001",
  "category": "Electronics",
  "brand": "TechBrand",
  "imageUrl": null,
  "active": true,
  "createdAt": "2026-01-29T00:00:00",
  "updatedAt": "2026-01-29T00:00:00"
}
```

#### Get Products with Pagination
```http
GET /products/page?page=0&size=10&sortBy=id&sortDir=asc
```

**Query Parameters:**
- `page` (int): Page number (0-indexed, default: 0)
- `size` (int): Page size (default: 10)
- `sortBy` (String): Sort field (default: id)
- `sortDir` (String): Sort direction (asc/desc, default: asc)

#### Search Products
```http
GET /products/search?keyword=laptop
```

**Query Parameters:**
- `keyword` (String): Search term

#### Get Products by Category
```http
GET /products/category/Electronics
```

**Path Parameters:**
- `category` (String): Product category

#### Get Products by Brand
```http
GET /products/brand/TechBrand
```

**Path Parameters:**
- `brand` (String): Product brand

#### Get Products by Price Range
```http
GET /products/price-range?minPrice=100&maxPrice=2000
```

**Query Parameters:**
- `minPrice` (BigDecimal): Minimum price
- `maxPrice` (BigDecimal): Maximum price

#### Get Low Stock Products (Admin only)
```http
GET /products/low-stock?threshold=10
```

**Query Parameters:**
- `threshold` (int): Stock threshold (default: 10)

#### Get Latest Products
```http
GET /products/latest
```

#### Create Product (Admin only)
```http
POST /products
```

**Request Body:**
```json
{
  "name": "New Product",
  "description": "Product description",
  "price": 99.99,
  "quantity": 100,
  "sku": "NP-001",
  "category": "Category",
  "brand": "Brand",
  "imageUrl": "https://example.com/image.jpg"
}
```

**Response:**
```json
{
  "id": 2,
  "name": "New Product",
  "description": "Product description",
  "price": 99.99,
  "quantity": 100,
  "sku": "NP-001",
  "category": "Category",
  "brand": "Brand",
  "imageUrl": "https://example.com/image.jpg",
  "active": true,
  "createdAt": "2026-01-29T00:00:00",
  "updatedAt": "2026-01-29T00:00:00"
}
```

#### Update Product (Admin only)
```http
PUT /products/{id}
```

**Path Parameters:**
- `id` (Long): Product ID

**Request Body:**
```json
{
  "name": "Updated Product",
  "description": "Updated description",
  "price": 149.99,
  "quantity": 75,
  "sku": "UP-001",
  "category": "Updated Category",
  "brand": "Updated Brand",
  "imageUrl": "https://example.com/updated-image.jpg",
  "active": true
}
```

#### Delete Product (Admin only)
```http
DELETE /products/{id}
```

**Path Parameters:**
- `id` (Long): Product ID

### System Endpoints

#### Home
```http
GET /
```

**Response:**
```json
{
  "message": "Spring Boot REST API is running!",
  "version": "1.0.0",
  "status": "ACTIVE",
  "documentation": "/swagger-ui.html",
  "h2-console": "/h2-console",
  "team": "RSK World",
  "website": "https://rskworld.in",
  "contact": "help@rskworld.in",
  "year": "2026"
}
```

#### Health Check
```http
GET /health
```

**Response:**
```json
{
  "status": "UP",
  "application": "Spring Boot REST API",
  "version": "1.0.0",
  "timestamp": 1643328000000
}
```

## 🔒 Error Handling

### Error Response Format

```json
{
  "status": 404,
  "message": "Product not found with id: 999",
  "timestamp": 1643328000000
}
```

### Validation Error Response

```json
{
  "status": 400,
  "message": "Validation failed",
  "timestamp": 1643328000000,
  "validationErrors": {
    "name": "Name is required",
    "price": "Price must be greater than 0"
  }
}
```

### Common HTTP Status Codes

| Status Code | Description |
|-------------|-------------|
| 200 | OK - Request successful |
| 201 | Created - Resource created |
| 400 | Bad Request - Invalid input |
| 401 | Unauthorized - Authentication required |
| 403 | Forbidden - Insufficient permissions |
| 404 | Not Found - Resource not found |
| 500 | Internal Server Error - Server error |

## 🎯 Usage Examples

### Using cURL

#### Register User
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe"
  }'
```

#### Login
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "usernameOrEmail": "john_doe",
    "password": "password123"
  }'
```

#### Get Products
```bash
curl -X GET http://localhost:8080/products \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Create Product
```bash
curl -X POST http://localhost:8080/products \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "New Product",
    "description": "Product description",
    "price": 99.99,
    "quantity": 100,
    "sku": "NP-001",
    "category": "Electronics",
    "brand": "TechBrand"
  }'
```

### Using JavaScript (Fetch API)

```javascript
// Login
const login = async () => {
  const response = await fetch('http://localhost:8080/auth/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      usernameOrEmail: 'john_doe',
      password: 'password123'
    })
  });
  
  const data = await response.json();
  localStorage.setItem('token', data.token);
  return data;
};

// Get Products
const getProducts = async () => {
  const token = localStorage.getItem('token');
  const response = await fetch('http://localhost:8080/products', {
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  });
  
  return await response.json();
};
```

## 📚 Additional Resources

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **H2 Console**: http://localhost:8080/h2-console
- **API Docs**: http://localhost:8080/api-docs

## 🤝 Support

For support and questions:
- **Email**: help@rskworld.in, support@rskworld.in
- **Website**: https://rskworld.in
- **Phone**: +91 93305 39277

---

© 2026 RSK World. All rights reserved.
