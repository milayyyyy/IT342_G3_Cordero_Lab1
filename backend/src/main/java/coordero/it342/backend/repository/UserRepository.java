package coordero.it342.backend.repository;

import coordero.it342.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<User> findByEmailVerificationToken(String token);
    Optional<User> findByPasswordResetToken(String token);
    
    @Query("SELECT u FROM User u WHERE u.firstName LIKE :search OR u.lastName LIKE :search OR u.email LIKE :search")
    List<User> searchUsers(@Param("search") String search);
}

