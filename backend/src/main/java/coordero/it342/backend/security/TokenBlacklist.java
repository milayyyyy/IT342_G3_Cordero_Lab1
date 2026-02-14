package coordero.it342.backend.security;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenBlacklist {
    private final ConcurrentHashMap<String, Long> blacklist = new ConcurrentHashMap<>();

    public void blacklist(String token, long expiresAtMillis) {
        blacklist.put(token, expiresAtMillis);
    }

    public boolean isBlacklisted(String token) {
        Long exp = blacklist.get(token);
        if (exp == null) {
            return false;
        }
        if (System.currentTimeMillis() > exp) {
            blacklist.remove(token);
            return false;
        }
        return true;
    }
}
