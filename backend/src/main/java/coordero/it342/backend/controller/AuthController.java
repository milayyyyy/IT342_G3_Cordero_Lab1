package coordero.it342.backend.controller;

import coordero.it342.backend.dto.AuthResponse;
import coordero.it342.backend.dto.LoginRequest;
import coordero.it342.backend.dto.RegisterRequest;
import coordero.it342.backend.service.AuthService;
import coordero.it342.backend.security.JwtUtil;
import coordero.it342.backend.security.TokenBlacklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import coordero.it342.backend.dto.ErrorResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private TokenBlacklist tokenBlacklist;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @javax.validation.Valid RegisterRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), System.currentTimeMillis()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @javax.validation.Valid LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(e.getMessage(), System.currentTimeMillis()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Authorization header missing", System.currentTimeMillis()));
        }
        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Invalid token", System.currentTimeMillis()));
        }
        long expiresAt = jwtUtil.getExpirationMillis(token);
        tokenBlacklist.blacklist(token, expiresAt);

        return ResponseEntity.ok(new ErrorResponse("Logged out successfully", System.currentTimeMillis()));
    }

    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam String email, @RequestParam String token) {
        try {
            AuthResponse response = authService.verifyEmail(email, token);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), System.currentTimeMillis()));
        }
    }

    @PostMapping("/request-password-reset")
    public ResponseEntity<?> requestPasswordReset(@RequestParam String email) {
        try {
            authService.requestPasswordReset(email);
            return ResponseEntity.ok(new ErrorResponse("Password reset email sent", System.currentTimeMillis()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), System.currentTimeMillis()));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String resetToken = request.get("resetToken");
            String newPassword = request.get("newPassword");

            AuthResponse response = authService.resetPassword(email, resetToken, newPassword);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), System.currentTimeMillis()));
        }
    }
}
