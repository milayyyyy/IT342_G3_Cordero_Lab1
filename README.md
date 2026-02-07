# IT342 User Registration and Authentication System

A full-stack application demonstrating user registration and authentication functionality with Spring Boot backend and React frontend.

## Project Description

This system implements a comprehensive user authentication solution including:
- User registration with email validation
- Secure login with JWT token authentication
- Protected user endpoints requiring authentication
- BCrypt password encryption
- MySQL database integration
- Cross-origin request handling

## Technologies Used

### Backend
- **Java 17**
- **Spring Boot 3.5.10**
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database operations
- **JWT (JSON Web Tokens)** - Token-based authentication
- **BCrypt** - Password encryption
- **MySQL 8.0** - Database
- **Maven** - Build management
- **Lombok** - Boilerplate code reduction

### Frontend
- **React 18.2**
- **React Router v6** - Client-side routing
- **Axios** - HTTP client
- **CSS3** - Styling

### Database
- **MySQL 8.0**
- Database name: `it342_auth_db`

## Project Structure

```
IT342_G3_Cordero_Lab1/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/coordero/it342/backend/
│   │   │   │   ├── controller/
│   │   │   │   │   ├── AuthController.java
│   │   │   │   │   └── UserController.java
│   │   │   │   ├── dto/
│   │   │   │   │   ├── RegisterRequest.java
│   │   │   │   │   ├── LoginRequest.java
│   │   │   │   │   ├── AuthResponse.java
│   │   │   │   │   └── UserResponse.java
│   │   │   │   ├── entity/
│   │   │   │   │   └── User.java
│   │   │   │   ├── repository/
│   │   │   │   │   └── UserRepository.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── AuthService.java
│   │   │   │   │   └── UserService.java
│   │   │   │   ├── security/
│   │   │   │   │   ├── JwtUtil.java
│   │   │   │   │   └── JwtAuthenticationFilter.java
│   │   │   │   ├── config/
│   │   │   │   │   └── SecurityConfig.java
│   │   │   │   └── BackendApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   │       └── java/coordero/it342/backend/
│   │           └── BackendApplicationTests.java
│   ├── pom.xml
│   ├── .mvn/wrapper/
│   ├── .gitignore
│   ├── .gitattributes
│   └── HELP.md
├── web/
│   ├── src/
│   │   ├── pages/
│       │   ├── Register.js
│       │   ├── Login.js
│       │   └── Dashboard.js
│       ├── context/
│       │   └── AuthContext.js
│       ├── api/
│       │   └── apiClient.js
│       ├── components/
│       │   └── PrivateRoute.js
│       ├── styles/
│       │   ├── global.css
│       │   ├── auth.css
│       │   └── dashboard.css
│       ├── App.js
│       └── index.js
│   ├── public/
│   ├── package.json
│   └── README.md
├── mobile/ (To be implemented)
├── docs/
├── README.md
└── TASK_CHECKLIST.md
```

## Prerequisites

- Java 17 or higher
- Node.js 16 or higher
- npm or yarn
- MySQL 8.0
- Visual Studio Code or any IDE

## Setup Instructions

### Database Setup

1. Install MySQL 8.0
2. Create database and user:
```sql
CREATE DATABASE it342_auth_db;
GRANT ALL PRIVILEGES ON it342_auth_db.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

### Backend Setup

1. Navigate to the backend directory:
```bash
cd backend/backend
```

2. Install dependencies and build:
```bash
./mvnw clean install
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

The backend API will run on `http://localhost:8080`

### Web Application Setup

1. Navigate to the web directory:
```bash
cd web
```

2. Install dependencies:
```bash
npm install
```

3. Run the development server:
```bash
npm start
```

The web application will open at `http://localhost:3000`

### Mobile App Setup

Mobile application development will be done in the next laboratory session.

## API Endpoints

### Authentication Endpoints

#### POST /api/auth/register
Register a new user
- **Request Body:**
```json
{
  "email": "user@example.com",
  "password": "securepassword",
  "firstName": "John",
  "lastName": "Doe"
}
```
- **Response:** 
```json
{
  "id": 1,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe"
}
```

#### POST /api/auth/login
Login with existing credentials
- **Request Body:**
```json
{
  "email": "user@example.com",
  "password": "securepassword"
}
```
- **Response:**
```json
{
  "id": 1,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe"
}
```

### User Endpoints (Protected)

#### GET /api/user/me
Get current user profile (requires JWT token)
- **Headers:** Authorization: Bearer {token}
- **Response:**
```json
{
  "id": 1,
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "createdAt": 1707340800000
}
```

## Features Implemented

### Session 1 (Current)

#### Backend
- [x] POST /api/auth/register - User registration
- [x] POST /api/auth/login - User login
- [x] GET /api/user/me - Get current user profile (protected)
- [x] MySQL database connection
- [x] BCrypt password encryption
- [x] JWT token authentication
- [x] CORS configuration

#### Frontend
- [x] Register page with form validation
- [x] Login page with email verification
- [x] Dashboard/Profile page (protected)
- [x] Logout functionality
- [x] Private route protection
- [x] Token-based authentication

### Session 2 (Future)
- [ ] Mobile application implementation
- [ ] Additional user features
- [ ] Email verification
- [ ] Password reset functionality

## Testing the Application

1. Start the backend server
2. Start the web application
3. Navigate to http://localhost:3000
4. Test registration with a new account
5. Test login with registered credentials
6. View user profile on the dashboard
7. Test logout functionality

## Session 1 Deliverables

- ✓ Backend API with authentication
- ✓ React web application with login/register pages
- ✓ Protected routes and JWT authentication
- ✓ User profile page
- ✓ MySQL database integration
- ✓ BCrypt password encryption
- ✓ Documentation and README

## Notes

- JWT tokens expire after 24 hours
- Password must be sent as plain text during registration/login (HTTPS should be used in production)
- All endpoints follow RESTful conventions
- CORS is configured for localhost development

## Future Enhancements

- Email verification
- Password reset functionality
- Two-factor authentication
- Role-based access control (RBAC)
- Social login integration
- User search and management
- Activity logging
- Admin dashboard

## Authors

- Cordero, G. (Group, Session 1)

## Due Date

Session 1: February 7, 2026 at 2:00 PM
