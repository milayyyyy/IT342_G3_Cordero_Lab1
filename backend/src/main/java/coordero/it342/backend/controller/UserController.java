package coordero.it342.backend.controller;

import coordero.it342.backend.dto.UserResponse;
import coordero.it342.backend.dto.UpdateUserRequest;
import coordero.it342.backend.entity.ActivityLog;
import coordero.it342.backend.service.UserService;
import coordero.it342.backend.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import coordero.it342.backend.dto.ErrorResponse;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ActivityLogService activityLogService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        try {
            String email = (String) authentication.getPrincipal();
            UserResponse user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateProfile(Authentication authentication, @RequestBody UpdateUserRequest request) {
        try {
            String email = (String) authentication.getPrincipal();
            UserResponse updatedUser = userService.updateUser(email, request);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), System.currentTimeMillis()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(@RequestParam String query) {
        try {
            List<UserResponse> results = userService.searchUsers(query);
            return ResponseEntity.ok(results);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), System.currentTimeMillis()));
        }
    }

    @GetMapping("/activity")
    public ResponseEntity<?> getActivity(Authentication authentication) {
        try {
            String email = (String) authentication.getPrincipal();
            Long userId = userService.getUserIdByEmail(email);
            List<ActivityLog> activity = activityLogService.getUserActivity(userId);
            return ResponseEntity.ok(activity);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), System.currentTimeMillis()));
        }
    }

    @GetMapping("/activity/recent")
    public ResponseEntity<?> getRecentActivity(Authentication authentication, @RequestParam(defaultValue = "24") Long hoursBack) {
        try {
            String email = (String) authentication.getPrincipal();
            Long userId = userService.getUserIdByEmail(email);
            List<ActivityLog> activity = activityLogService.getRecentActivity(userId, hoursBack);
            return ResponseEntity.ok(activity);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), System.currentTimeMillis()));
        }
    }
}
