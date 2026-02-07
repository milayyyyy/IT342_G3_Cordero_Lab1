package coordero.it342.backend.util;

public class PasswordValidator {
    private static final int MIN_LENGTH = 8;
    private static final String UPPERCASE_PATTERN = ".*[A-Z].*";
    private static final String LOWERCASE_PATTERN = ".*[a-z].*";
    private static final String DIGIT_PATTERN = ".*\\d.*";
    private static final String SPECIAL_CHAR_PATTERN = ".*[!@#$%^&*()_+\\-=\\[\\]{};:,.<>?].*";

    public static String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Password cannot be empty";
        }

        if (password.length() < MIN_LENGTH) {
            return "Password must be at least 8 characters long";
        }

        if (!password.matches(UPPERCASE_PATTERN)) {
            return "Password must contain at least one uppercase letter (A-Z)";
        }

        if (!password.matches(LOWERCASE_PATTERN)) {
            return "Password must contain at least one lowercase letter (a-z)";
        }

        if (!password.matches(DIGIT_PATTERN)) {
            return "Password must contain at least one digit (0-9)";
        }

        if (!password.matches(SPECIAL_CHAR_PATTERN)) {
            return "Password must contain at least one special character (!@#$%^&*()_+-=[]{}|;:,.<>?)";
        }

        return null;
    }

    public static boolean isValid(String password) {
        return validatePassword(password) == null;
    }
}
