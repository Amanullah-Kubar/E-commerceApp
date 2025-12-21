# E-Commerce Backend (Spring Boot + MySQL)

This repository contains a simple Spring Boot backend for an e-commerce demo application. It provides RESTful CRUD APIs for managing products and can be consumed by a frontend client (e.g., the React + Vite frontend in [`e-commerce-Frontend`](https://github.com/Amanullah-Kubar/e-commerce-Frontend)).

---

## Features

- **CRUD operations for products**  
  - Create, read, update, delete products via REST APIs.
- **Exception handling**  
  - Returns meaningful HTTP status codes and error messages for invalid requests.
- **DTO pattern**  
  - Separates entity models from API responses.
- **Layered architecture**  
  - Controller → Service → Repository → Model separation.
- **Database integration**  
  - MySQL used for persisting product data.

---

## Tech Stack

- **Backend Framework**: Spring Boot  
- **Persistence**: Spring Data JPA, Hibernate, MySQL  
- **Build Tool**: Maven  
- **Java Version**: 17 (or specify your version)  
- **Testing**: JUnit / Spring Boot Test  

---

## Installation

1. Clone the repository:

```bash
git clone https://github.com/Amanullah-Kubar/E-commerceApp.git
```

Navigate into the project directory:

```bash
cd E-commerceApp
```

Configure MySQL database connection in src/main/resources/application.properties:
properties
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
Build and run the project:

```bash
./mvnw spring-boot:run
```

The backend will start on http://localhost:8080 by default.

Project Structure
```bash
src/main/java/com/yourpackage/
├── controller     # REST controllers for product endpoints
├── service        # Business logic services
├── repository     # Spring Data JPA repositories
├── model          # Entity classes representing database tables
├── dto            # Data Transfer Objects for API responses/requests
└── EcommerceAppApplication.java  # Main Spring Boot application entry
```
graphql
```bash
src/main/resources/
├── application.properties  # Database config and app properties
└── data.sql                # Optional: initial seed data
```
## API Endpoints (Example)
Method	Endpoint	Description
GET	/products	List all products
GET	/products/{id}	Get product by ID
POST	/products	Create a new product
PUT	/products/{id}	Update product by ID
DELETE	/products/{id}	Delete product by ID

All endpoints return appropriate HTTP status codes (200, 201, 400, 404, etc.) and JSON responses.

## Error Handling
Custom exceptions with meaningful messages.

Global exception handler using @ControllerAdvice.

Returns structured error responses with HTTP status and message.

## Future Improvements
Add endpoints for orders, users, and authentication.

Implement pagination and filtering for products.

Add logging and monitoring.

Include unit and integration tests for all endpoints.

