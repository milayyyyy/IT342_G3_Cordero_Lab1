package coordero.it342.backend.util;

public class UsernameValidator {
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 50;
    private static final String VALID_PATTERN = "^[a-zA-Z0-9_-]+$";

    public static String validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            return "Username cannot be empty";
        }

        if (username.length() < MIN_LENGTH) {
            return "Username must be at least 3 characters long";
        }

        if (username.length() > MAX_LENGTH) {
            return "Username must be no more than 50 characters";
        }

        if (!username.matches(VALID_PATTERN)) {
            return "Username must contain only alphanumeric characters, underscores, and hyphens";
        }

        return null;
    }

    public static boolean isValid(String username) {
        return validateUsername(username) == null;
    }
}
