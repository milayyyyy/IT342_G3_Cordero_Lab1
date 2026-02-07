package coordero.it342.backend.service;

import coordero.it342.backend.entity.ActivityLog;
import coordero.it342.backend.repository.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityLogService {
    
    @Autowired
    private ActivityLogRepository activityLogRepository;
    
    public ActivityLog logActivity(Long userId, String action, String description, String ipAddress, String userAgent) {
        ActivityLog log = ActivityLog.builder()
                .userId(userId)
                .action(action)
                .description(description)
                .ipAddress(ipAddress)
                .userAgent(userAgent)
                .build();
        return activityLogRepository.save(log);
    }
    
    public List<ActivityLog> getUserActivity(Long userId) {
        return activityLogRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
    
    public List<ActivityLog> getRecentActivity(Long userId, Long hoursBack) {
        Long startTime = System.currentTimeMillis() - (hoursBack * 60 * 60 * 1000);
        return activityLogRepository.findRecentActivityForUser(userId, startTime);
    }
}
