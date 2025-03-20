
# AuthShield - Spring Boot Authentication System

## [Design](https://veil-canid-d6e.notion.site/Auth-App-1bc119f29e988024944ee1e0d11abb49)
## 🚀 Overview
AuthShield is a **secure authentication system** built with **Spring Boot, JWT, and Spring Security**, providing robust **user authentication, role-based access control (RBAC), and session management**. The system ensures safe login, token-based authorization, and seamless API security.

## 🛠️ Features
- **JWT-based Authentication**: Secure user login with access and refresh tokens.
- **Role-Based Access Control (RBAC)**: Define different user roles with specific permissions.
- **Password Hashing**: Uses `bcrypt` for enhanced security.
- **Token Expiration & Refresh Mechanism**: Prevent unauthorized access.
- **Spring Security Integration**: Custom authentication filters and security configurations.
- **RESTful API Design**: Efficient endpoints for user authentication and role management.

## 🏗️ Tech Stack
- **Backend**: Spring Boot, Spring Security, JWT, JPA
- **Database**: MySQL
- **Testing**: JUnit, Mockito
- **Other**: Lombok, Gradle

## 📂 Project Structure
```
AuthShield/
│── src/main/java/com/authshield/
│   ├── config/      # Security & JWT Configurations
│   ├── controller/  # API Controllers
│   ├── dto/         # Data Transfer Objects
│   ├── model/       # Entity Models
│   ├── repository/  # JPA Repositories
│   ├── service/     # Business Logic
│── src/main/resources/
│   ├── application.properties

```

## 🔧 Setup & Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/AuthShield.git
   cd AuthShield
   ```
2. Configure the `application.properties` file:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/auth_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   jwt.secret=your_secret_key
   ```
3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## 📌 API Endpoints
### Authentication
- **`POST /api/auth/register`** → Register a new user
- **`POST /api/auth/login`** → Authenticate user and get JWT token
- **`POST /api/auth/refresh`** → Refresh access token using refresh token

### User Management
- **`GET /api/users/me`** → Get current user details
- **`GET /api/users/{id}`** → Get user by ID (Admin only)

## 🛡️ Security Implementation
- Uses **Spring Security filters** for authentication and authorization.
- Enforces **role-based access control** for different endpoints.
- Implements **JWT validation & token refresh logic**.


⭐ **Star** this repository if you found it useful!
