package coordero.it342.backend.service;

import coordero.it342.backend.dto.AuthResponse;
import coordero.it342.backend.dto.LoginRequest;
import coordero.it342.backend.dto.RegisterRequest;
import coordero.it342.backend.entity.User;
import coordero.it342.backend.repository.UserRepository;
import coordero.it342.backend.security.JwtUtil;
import coordero.it342.backend.util.PasswordValidator;
import coordero.it342.backend.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private ActivityLogService activityLogService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthResponse register(RegisterRequest request) {
        // Validate password strength
        String passwordError = PasswordValidator.validatePassword(request.getPassword());
        if (passwordError != null) {
            throw new RuntimeException(passwordError);
        }

        // Check password confirmation
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Generate email verification token
        String verificationToken = emailUtil.generateVerificationToken();

        // Extract username from email (part before @)
        String username = request.getEmail().substring(0, request.getEmail().indexOf('@'));

        // Create new user
        User user = User.builder()
                .username(username)
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .isActive(true)
                .emailVerified(false)
                .emailVerificationToken(verificationToken)
                .build();

        User savedUser = userRepository.save(user);

        // Send verification email
        emailUtil.sendVerificationEmail(savedUser.getEmail(), verificationToken);

        // Generate token (but not verified yet)
        String token = jwtUtil.generateToken(savedUser.getEmail());

        return AuthResponse.builder()
                .id(savedUser.getId())
                .token(token)
                .tokenType("Bearer")
                .expiresIn(86400L)
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        User user = userOptional.get();

        // Check if account is active
        if (!user.getIsActive()) {
            throw new RuntimeException("Account is inactive");
        }

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Update last login time
        user.setLastLoginAt(System.currentTimeMillis());
        userRepository.save(user);

        // Generate token
        String token = jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .id(user.getId())
                .token(token)
                .tokenType("Bearer")
                .expiresIn(86400L)
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public AuthResponse verifyEmail(String email, String token) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        if (!token.equals(user.getEmailVerificationToken())) {
            throw new RuntimeException("Invalid verification token");
        }

        user.setEmailVerified(true);
        user.setEmailVerificationToken(null);
        User savedUser = userRepository.save(user);

        String newToken = jwtUtil.generateToken(savedUser.getEmail());

        return AuthResponse.builder()
                .id(savedUser.getId())
                .token(newToken)
                .tokenType("Bearer")
                .expiresIn(86400L)
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .build();
    }

    public void requestPasswordReset(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        String resetToken = emailUtil.generateVerificationToken();
        user.setPasswordResetToken(resetToken);
        user.setPasswordResetTokenExpiry(System.currentTimeMillis() + (60 * 60 * 1000)); // 1 hour expiry
        userRepository.save(user);

        emailUtil.sendPasswordResetEmail(user.getEmail(), resetToken);
    }

    public AuthResponse resetPassword(String email, String resetToken, String newPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        // Validate token
        if (!resetToken.equals(user.getPasswordResetToken())) {
            throw new RuntimeException("Invalid reset token");
        }

        // Check token expiry
        if (System.currentTimeMillis() > user.getPasswordResetTokenExpiry()) {
            throw new RuntimeException("Reset token has expired");
        }

        // Validate new password
        String passwordError = PasswordValidator.validatePassword(newPassword);
        if (passwordError != null) {
            throw new RuntimeException(passwordError);
        }

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        user.setPasswordResetToken(null);
        user.setPasswordResetTokenExpiry(null);
        User savedUser = userRepository.save(user);

        String token = jwtUtil.generateToken(savedUser.getEmail());

        return AuthResponse.builder()
                .id(savedUser.getId())
                .token(token)
                .tokenType("Bearer")
                .expiresIn(86400L)
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .build();
    }
}
