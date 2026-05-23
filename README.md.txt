# E-Commerce Backend API

A secure and scalable E-Commerce Backend Application built using Spring Boot, Spring Security, JWT Authentication, Hibernate, and MySQL.

## 🚀 Features

- User Registration and Login
- JWT Authentication and Authorization
- Role-Based Access Control (USER / ADMIN)
- Product Management APIs
- Cart Management System
- Order Placement and Checkout
- Order Items Management
- Inventory Stock Validation
- Global Exception Handling
- RESTful API Design
- MySQL Database Integration
- Transaction Management using @Transactional

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Hibernate / JPA
- MySQL
- Maven

---

## 📂 Project Structure

- Controller Layer
- Service Layer
- Repository Layer
- Entity Layer
- DTO Layer
- Security Layer

---

## 🔐 Authentication

This project uses JWT-based authentication.

### Public APIs
- Register User
- Login User

### Secured APIs
- Product APIs
- Cart APIs
- Order APIs

---

## 📦 Main Modules

### User Module
- User Registration
- User Login
- Role Management

### Product Module
- Add Product
- Get Products
- Update Product
- Delete Product

### Cart Module
- Add to Cart
- View Cart
- Remove Cart Item

### Order Module
- Place Order
- View User Orders

---

## ⚙️ Database Configuration

Update your `application.properties` file with your MySQL credentials.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=yourpassword