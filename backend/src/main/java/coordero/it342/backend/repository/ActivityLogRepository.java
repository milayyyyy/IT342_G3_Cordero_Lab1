package coordero.it342.backend.repository;

import coordero.it342.backend.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    @Query("SELECT a FROM ActivityLog a WHERE a.userId = :userId ORDER BY a.createdAt DESC")
    List<ActivityLog> findByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId);

    @Query("SELECT a FROM ActivityLog a WHERE a.userId = :userId AND a.createdAt >= :startTime ORDER BY a.createdAt DESC")
    List<ActivityLog> findRecentActivityForUser(@Param("userId") Long userId, @Param("startTime") Long startTime);
}
