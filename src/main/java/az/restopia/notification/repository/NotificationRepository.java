package az.restopia.notification.repository;

import az.restopia.notification.domain.entity.Notification;
import az.restopia.notification.domain.enums.NotificationCategory;
import az.restopia.notification.domain.enums.NotificationPriority;
import az.restopia.commons.domain.enums.DeleteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByDeleteStatusOrderByCreatedDateTimeDesc(DeleteStatus deleteStatus);

    Optional<Notification> findByIdAndDeleteStatus(Long id, DeleteStatus deleteStatus);

    @Query("SELECT n FROM Notification n WHERE n.deleteStatus = :deleteStatus " +
           "AND (LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(n.body) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(n.fromUser) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(n.toUser) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "ORDER BY n.createdDateTime DESC")
    List<Notification> searchNotifications(@Param("keyword") String keyword, @Param("deleteStatus") DeleteStatus deleteStatus);

    List<Notification> findAllByPriorityAndDeleteStatusOrderByCreatedDateTimeDesc(NotificationPriority priority, DeleteStatus deleteStatus);

    List<Notification> findAllByCategoryAndDeleteStatusOrderByCreatedDateTimeDesc(NotificationCategory category, DeleteStatus deleteStatus);

    List<Notification> findAllByFromUserAndDeleteStatusOrderByCreatedDateTimeDesc(String fromUser, DeleteStatus deleteStatus);

    @Modifying
    @Query("UPDATE Notification n SET n.isRead = true WHERE n.deleteStatus = :deleteStatus")
    void markAllAsRead(@Param("deleteStatus") DeleteStatus deleteStatus);

    @Modifying
    @Query("UPDATE Notification n SET n.deleteStatus = :deleteStatus WHERE n.deleteStatus = :currentStatus")
    void deleteAllNotifications(@Param("deleteStatus") DeleteStatus deleteStatus, @Param("currentStatus") DeleteStatus currentStatus);
}