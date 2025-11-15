# Order System API

A RESTful API for managing orders. Built with **Java 21**, **Spring Boot 3.5.6**.
The project provides CRUD operations for customers, orders and products, following best practices in REST design and documentation wint OpenAPI/Swagger.

## Overview

The **Order System API** allows you to:
- Register users
- Manage users, orders and products (create, list, update, delete)
- Place orders as a user
- Add items linked to products in an order

## Tech Stack

![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?logo=springboot)

## Project Structure
```
com.raptor.ordersystem
├── config
│   └── BasicSecurity.java
├── controller
│   ├── OrderController.java
│   ├── OrderItemController.java
│   ├── ProductController.java
│   └── UserController.java
├── dto
│   ├── AddOrderItemDTO.java
│   ├── CreateOrderDTO.java
│   ├── CreateOrderItemDTO.java
│   ├── CreateUserDTO.java
│   ├── OrderDTO.java
│   ├── OrderItemDTO.java
│   ├── OrderSummaryDTO.java
│   ├── ProductDTO.java
│   ├── UserDTO.java
│   ├── UserOrdersDTO.java
│   └── UserSummaryDTO.java
├── entity
│   ├── Order.java
│   ├── OrderItem.java
│   ├── Product.java
│   ├── User.java
│   └── UserPrincipal.java
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
│   ├── OrderItemService.java
│   ├── OrderService.java
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
- [ ] Add user's role business logic
- [ ] Add JWT

## 👤 Author

**Felipe Salles Lopes**  
- 🌐 [GitHub](https://github.com/El-Raptor)  
- 💼 [LinkedIn](https://linkedin.com/in/felipe-salles-lopes)

---

## ⚖️ License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.