# Functional Requirements Specification (FRS)

## Overview
This document specifies the functional requirements for the IT342 User Registration and Authentication System, including Web and Mobile applications backed by a Spring Boot REST API.

## Scope
- Web application: React (Register, Login, Dashboard/Profile, Logout)
- Mobile application: Android (Kotlin) (Register, Login, Profile, Logout)
- Backend: Spring Boot with JWT authentication, MySQL

## Actors
- Visitor: Unauthenticated user
- Registered User: Authenticated user

## Functional Requirements
1. Registration
   - Inputs: firstName, lastName, email, password, confirmPassword
   - Validations: email format, password length >= 8, password match
   - Output: AuthResponse with token and user details
2. Login
   - Inputs: email, password
   - Output: AuthResponse with token
3. Protected Profile
   - Access /api/user/me via Authorization: Bearer token
4. Logout
   - Endpoint /api/auth/logout invalidates current JWT via blacklist
5. Error Handling
   - Consistent responses using ErrorResponse { message, timestamp }
6. Email Verification & Password Reset (optional)
   - Tokens via EmailUtil; endpoints implemented

## API Endpoints
- POST /api/auth/register
- POST /api/auth/login
- POST /api/auth/logout
- GET /api/user/me
- POST /api/auth/verify-email
- POST /api/auth/request-password-reset
- POST /api/auth/reset-password

## Mobile App Flows
- RegisterActivity -> ProfileActivity (on success)
- LoginActivity -> ProfileActivity (on success)
- ProfileActivity -> Logout -> LoginActivity

## Screenshots
Place screenshots in /docs/screenshots:
- web-register.png, web-login.png, web-dashboard.png
- mobile-register.png, mobile-login.png, mobile-profile.png

## Diagrams
Include updated architecture and sequence diagrams if implementation differs from design.

## Non-Functional Requirements
- Security: JWT, BCrypt
- Performance: Stateless API
- Compatibility: Android minSdk 24, Web modern browsers

## Assumptions
- Development on localhost; Android uses 10.0.2.2 to reach backend

## References
- Repository: https://github.com/milayyyyy/IT342_G3_Cordero_Lab1.git
