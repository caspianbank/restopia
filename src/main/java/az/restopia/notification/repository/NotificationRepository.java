package az.restopia.notification.repository;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.notification.domain.entity.Notification;
import az.restopia.notification.domain.enums.NotificationCategory;
import az.restopia.notification.domain.enums.NotificationPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByTenantCodeAndDeleteStatus(String tenantCode, DeleteStatus deleteStatus);

    Optional<Notification> findByIdAndTenantCodeAndDeleteStatus(Long id, String tenantCode, DeleteStatus deleteStatus);

    List<Notification> findByTenantCodeAndPriorityAndDeleteStatus(String tenantCode, NotificationPriority priority,
                                                                  DeleteStatus deleteStatus);

    List<Notification> findByTenantCodeAndCategoryAndDeleteStatus(String tenantCode, NotificationCategory category,
                                                                  DeleteStatus deleteStatus);

    List<Notification> findByTenantCodeAndFromUserAndDeleteStatus(String tenantCode, String fromUser,
                                                                  DeleteStatus deleteStatus);

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.tenantCode = :tenantCode " +
            "AND n.deleteStatus = 'NOT_DELETED' AND n.isRead = false")
    Long countUnreadNotificationsByTenantCode(@Param("tenantCode") String tenantCode);

    @Query("SELECT n FROM Notification n WHERE n.tenantCode = :tenantCode " +
            "AND n.deleteStatus = 'NOT_DELETED' " +
            "AND (LOWER(n.title) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(n.body) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(n.fromUser) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(n.toUser) LIKE LOWER(CONCAT('%', :q, '%')))")
    List<Notification> searchNotifications(@Param("tenantCode") String tenantCode,
                                           @Param("q") String q);

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isRead = true, n.modifiedDateTime = CURRENT_TIMESTAMP " +
            "WHERE n.tenantCode = :tenantCode AND n.deleteStatus = 'NOT_DELETED' AND n.isRead = false")
    void markAllAsReadByTenantCode(@Param("tenantCode") String tenantCode);

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.deleteStatus = 'DELETED', n.modifiedDateTime = CURRENT_TIMESTAMP " +
            "WHERE n.tenantCode = :tenantCode AND n.deleteStatus = 'NOT_DELETED'")
    void softDeleteAllByTenantCode(@Param("tenantCode") String tenantCode);
}