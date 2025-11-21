# Order System API
![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.5.6-brightgreen?logo=springsecurity)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17.0-brightgreen?logo=postgreSQL)

A RESTful API for managing orders. Built with **Java 21**, **Spring Boot 3.5.6**.
The project provides CRUD operations for customers, orders and products, following best practices in REST design and documentation wint OpenAPI/Swagger.

## Overview

The **Order System API** allows you to:
- Register users
- Manage users, orders and products (create, list, update, delete)
- Place orders as a user
- Add items linked to products in an order

## Tech Stack



## Project Structure
```
com.raptor.ordersystem
├── config
│   └── BasicSecurity.java
├── controller
│   ├── OrderController.java
│   ├── OrderItemController.java [DEPRECATED]
│   ├── ProductController.java
│   └── UserController.java
├── dto
│   ├── AddOrderItemDTO.java
│   ├── CreateOrderDTO.java
│   ├── CreateOrderItemDTO.java
│   ├── CreateUserDTO.java
│   ├── OrderDTO.java
│   ├── OrderItemDTO.java [DEPRECATED]
│   ├── OrderSummaryDTO.java
│   ├── ProductDTO.java
│   ├── UserDTO.java
│   ├── UserLoginDTO.java
│   ├── UserOrdersDTO.java
│   └── UserSummaryDTO.java
├── entity
│   ├── Order.java
│   ├── OrderItem.java
│   ├── Product.java
│   ├── User.java
│   └── UserPrincipal.java
├── exception
│   ├── ApiError.java
│   └── GlobalExceptionHandler.java
├── filter
│   └── JwtFilter.java
├── mapper
│   ├── OrderItemMapper.java
│   ├── OrderMapper.java
│   ├── ProductMapper.java
│   └── UserMapper.java
├── repository
│   ├── OrderItemRepository.java
│   ├── OrderRepository.java
│   ├── ProductRepository.java
│   └── UserRepository.java
├── service
│   ├── CustomUserDetailsService.java
│   ├── JWTService.java
│   ├── OrderItemService.java
│   ├── OrderService.java [DEPRECATED]
│   ├── ProductService.java
│   └── UserService.java
├── utility
│   └── Role.java
└── OrderSystemApplication.java
```

## Running The Application


## Dependencies

- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-validation`
- `springdoc-openapi-starter-webmvc-ui`
- `postgresql`
- `lombok`
- `flyway-core`
- `flyway-database-postgresql`
- `jjwt`
- `jjwt-jackson`
- `jjwt-impl`

## Roadmap

### Version 0.1.0
- [x] Create initial project structure with Spring Boot
- [x] Add PostgreSQL database.
- [x] Add Flyway migrations
- [x] Implement the CRUD for Users, Orders, OrderItems, and Products
- [x] Implement Spring Security and Login
- [x] Dockerize app with Dockerfile and Docker Compose yaml file
- [x] Create CI workflow for GitHub Actions

### Version 0.2.0 - Business Logic
- [x] Add user's role business logic
- [x] Add user's login business logic
- [x] Add order and order items business logic
- [x] Add global exception handler
- [x] Add JWT

## 👤 Author

**Felipe Salles Lopes**  
- 🌐 [GitHub](https://github.com/El-Raptor)  
- 💼 [LinkedIn](https://linkedin.com/in/felipe-salles-lopes)

---

## ⚖️ License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.