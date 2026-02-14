# IT342 User Registration and Authentication System - Task Checklist

## DONE

### Backend Implementation
- ✓ Create User entity with JPA annotations - Commit: 3cfbf91
- ✓ Create UserRepository interface - Commit: 3cfbf91
- ✓ Implement DTOs (RegisterRequest, LoginRequest, AuthResponse, UserResponse) - Commit: 3cfbf91
- ✓ Implement AuthService with registration and login logic - Commit: 3cfbf91
- ✓ Implement UserService for profile retrieval - Commit: 3cfbf91
- ✓ Create JwtUtil for token generation and validation - Commit: 3cfbf91
- ✓ Create JwtAuthenticationFilter for token validation - Commit: 3cfbf91
- ✓ Create SecurityConfig for Spring Security configuration - Commit: 3cfbf91
- ✓ Create AuthController with /api/auth/register and /api/auth/login endpoints - Commit: 3cfbf91
- ✓ Create UserController with /api/user/me endpoint - Commit: 3cfbf91
- ✓ Add JWT dependencies to pom.xml - Commit: 3cfbf91
- ✓ Configure MySQL database connection in application.properties - Commit: 3cfbf91
- ✓ Configure Spring Security with JWT authentication - Commit: 3cfbf91
- ✓ Create BackendApplication main class - Commit: 3cfbf91
- ✓ Removed username field to align with ERD - Commit: 16feef8
- ✓ Implement logout endpoint with JWT blacklist - Commit: pending
- ✓ Add global exception handler and validation annotations - Commit: pending

### Frontend Implementation
- ✓ Create React project structure - Commit: 16feef8
- ✓ Create AuthContext for global authentication state - Commit: 16feef8
- ✓ Create apiClient with axios for API communication - Commit: 16feef8
- ✓ Implement Register page component - Commit: 16feef8
- ✓ Implement Login page component - Commit: 16feef8
- ✓ Implement Dashboard/Profile page component - Commit: 16feef8
- ✓ Create PrivateRoute component for route protection - Commit: 16feef8
- ✓ Create CSS styles for authentication pages - Commit: 16feef8
- ✓ Create CSS styles for dashboard page - Commit: 16feef8
- ✓ Setup React Router for navigation - Commit: 16feef8
- ✓ Implement logout functionality - Commit: 16feef8
- ✓ Configure CORS for frontend-backend communication - Commit: 3cfbf91
- ✓ Fixed ESLint warnings in Dashboard and Register components - Commit: aac262d

### Documentation & Repository Setup
- ✓ Update main README.md with project description - Commit: 24dfef1
- ✓ Update README.md with technologies used - Commit: 24dfef1
- ✓ Update README.md with setup instructions - Commit: 24dfef1
- ✓ Update README.md with API endpoints documentation - Commit: 24dfef1
- ✓ Create individual README.md for web application - Commit: 16feef8
- ✓ Add project documentation structure - Commit: 4842a7d
- ✓ Add mobile app placeholder - Commit: aac262d
- ✓ Update FRS content in docs (to export PDF) - Commit: pending
- ✓ Add mobile app (Android Kotlin) initial implementation - Commit: pending

## IN-PROGRESS

None - All planned tasks for Session 1 are complete.

## TODO

### Backend Enhancements
- ✓ Add email verification functionality - Commit: a2d33e1
- ✓ Implement password reset endpoint - Commit: a2d33e1
- ✓ Add user update profile endpoint - Commit: a2d33e1
- ✓ Implement role-based access control (RBAC) - Entity & Repository created
- ✓ Add user search and filtering - Commit: a2d33e1
- ✓ Create activity logging system - Commit: a2d33e1
- [ ] Add request validation and error handling improvements
- [ ] Create API documentation (Swagger/OpenAPI)

### Frontend Enhancements
- ✓ Add loading states and better error messages - Commit: 3566940
- [ ] Implement remember me functionality
- ✓ Add form validation feedback - Commit: 3566940
- ✓ Create user settings/profile edit page - Commit: 3566940
- [ ] Add search and filter users functionality
- ✓ Implement responsive design for mobile devices - Commit: 3566940
- ✓ Add dark mode support - Commit: 3566940
- [ ] Create admin dashboard

### Mobile Application
- ✓ Setup Android Kotlin project under /mobile - Commit: pending
- ✓ Create mobile registration page (Kotlin/Retrofit) - Commit: pending
- ✓ Create mobile login page (Kotlin/Retrofit) - Commit: pending
- ✓ Create mobile profile page (protected) - Commit: pending
- ✓ Implement logout functionality (token blacklist) - Commit: pending
- [ ] Create signed Android APK build

### Database
- [ ] Create backup and restore scripts
- [ ] Add database indexing for performance
- [ ] Create database migration scripts
- [ ] Add data validation at database level

### DevOps & Deployment
- [ ] Setup CI/CD pipeline
- [ ] Create Docker containers for backend
- [ ] Create Docker containers for frontend
- [ ] Setup environment configuration management
- [ ] Deploy to cloud platform (AWS/Azure/GCP)
- [ ] Setup monitoring and logging
- [ ] Create deployment documentation

### Testing
- [ ] Create unit tests for backend services
- [ ] Create integration tests for API endpoints
- [ ] Create frontend component tests
- [ ] Create end-to-end tests
- [ ] Setup test coverage reporting
- [ ] Create load testing scripts

### Documentation
- [ ] Create ERD (Entity Relationship Diagram)
- [ ] Create UML diagrams
- [ ] Create API testing guide
- [ ] Create deployment guide
- [ ] Create troubleshooting guide
- [ ] Create video tutorials
- [ ] Take screenshots of Web and Mobile UI for documentation

## Summary

### Completed: 45+ Tasks
- 20 Backend implementation and enhancement tasks
- 20+ Frontend implementation and enhancement tasks
- 10+ Documentation and repository setup tasks

### Status: Session 1 Complete - Session 2 In Progress
Core authentication functionality is complete. Session 2 enhancements for email verification, password reset, user profile updates, responsive design, and dark mode are now implemented.

### Session 1 Deliverables Met:
✓ Backend REST API with JWT authentication
✓ React web application with login and register pages
✓ Protected dashboard showing user profile
✓ MySQL database integration
✓ BCrypt password encryption
✓ Comprehensive README with setup instructions
✓ API endpoint documentation
✓ Git repository with individual commits
✓ Code aligned with ERD (email-only login)
✓ Fixed linting issues

### Session 2 Deliverables In Progress:
✓ Email verification functionality
✓ Password reset functionality
✓ User profile update endpoint
✓ Activity logging system
✓ User search and filtering
✓ Responsive design (mobile-friendly)
✓ Dark mode support
✓ User settings page
✓ Password reset page
✓ Email verification page
⏳ Role-based access control (RBAC) - entities created
⏳ Remember me functionality
⏳ User search frontend
⏳ Admin dashboard
⏳ Request validation improvements
⏳ Swagger/OpenAPI documentation

### GitHub Repository
- Repository: https://github.com/milayyyyy/IT342_G3_Cordero_Lab1.git
- Branch: main
- Total Commits: 11
- Latest Commit: 3566940 (Frontend enhancements)

### Session 2 Goals:
- Implement mobile application using React Native/Flutter
- Enhance frontend with additional features (responsive design, dark mode)
- Add backend improvements (email verification, password reset)
- Complete documentation with ERD and UML diagrams

## Notes

- All Java files follow Spring Boot conventions
- All React components use functional components with hooks
- JWT token expires after 24 hours
- Database tables are auto-created on first run via JPA
- CORS is configured for development on localhost:3000 and localhost:3001
- Project structure includes placeholders for mobile and docs folders
- ESLint warnings have been resolved
