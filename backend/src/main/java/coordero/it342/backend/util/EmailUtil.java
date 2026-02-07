package coordero.it342.backend.util;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class EmailUtil {
    
    public String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }
    
    public boolean sendVerificationEmail(String email, String token) {
        // Mock implementation - in production, use actual email service (JavaMailSender)
        System.out.println("Sending verification email to " + email);
        System.out.println("Verification token: " + token);
        return true;
    }
    
    public boolean sendPasswordResetEmail(String email, String token) {
        // Mock implementation
        System.out.println("Sending password reset email to " + email);
        System.out.println("Reset token: " + token);
        return true;
    }
    
    public String buildVerificationUrl(String token) {
        return "http://localhost:3000/verify-email?token=" + token;
    }
    
    public String buildPasswordResetUrl(String token) {
        return "http://localhost:3000/reset-password?token=" + token;
    }
}
