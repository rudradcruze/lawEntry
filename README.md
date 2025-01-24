# Law Entry Management System

A Spring Boot application for managing law cases and client information.

## Features

- User authentication and authorization
- CRUD operations for law entries
- Responsive UI with Bootstrap

## Tech Stack

- Java 21
- Spring Boot 2.7.14
- Spring Security
- Thymeleaf
- MySQL
- Lombok
- Maven

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/mohtashim/sami/
│   │       ├── config/
│   │       │   └── SecurityConfig.java
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       ├── static/
│       │   ├── css/
│       │   └── js/
│       └── templates/
```

## Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>annotationProcessor</scope>
    </dependency>
</dependencies>
```

## Setup & Installation

1. Configure database in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lawentry?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
```

2. Build and run:
```bash
mvn clean install
mvn spring-boot:run
```

## Database Schema

### User Table
```sql
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255)
);
```

### Law Entry Table
```sql
CREATE TABLE law_entry (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_name VARCHAR(255),
    date DATETIME,
    case_name VARCHAR(255),
    description TEXT(10000)
);
```

## API Endpoints

### Authentication
- POST `/do-login` - Login
- POST `/do-register` - Register
- GET `/logout` - Logout

### Law Entries
- GET `/` - View all
- POST `/lawEntry/new` - Create
- GET `/lawEntry/edit/{id}` - Edit
- GET `/lawEntry/delete/{id}` - Delete