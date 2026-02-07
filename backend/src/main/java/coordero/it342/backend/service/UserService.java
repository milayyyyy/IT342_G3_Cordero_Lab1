package coordero.it342.backend.service;

import coordero.it342.backend.dto.UserResponse;
import coordero.it342.backend.dto.UpdateUserRequest;
import coordero.it342.backend.entity.User;
import coordero.it342.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public Long getUserIdByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return userOptional.get().getId();
    }

    public UserResponse updateUser(String email, UpdateUserRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        // Check if new email is already taken
        if (!request.getEmail().equals(email) && userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUpdatedAt(System.currentTimeMillis());

        User updatedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(updatedUser.getId())
                .email(updatedUser.getEmail())
                .firstName(updatedUser.getFirstName())
                .lastName(updatedUser.getLastName())
                .isActive(updatedUser.getIsActive())
                .createdAt(updatedUser.getCreatedAt())
                .build();
    }

    public List<UserResponse> searchUsers(String query) {
        List<User> users = userRepository.searchUsers("%" + query + "%");
        return users.stream().map(user -> UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .build())
                .collect(Collectors.toList());
    }
}
