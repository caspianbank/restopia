package az.restopia.notification.service.impl;

import az.restopia.notification.domain.entity.Notification;
import az.restopia.notification.domain.enums.NotificationCategory;
import az.restopia.notification.domain.enums.NotificationPriority;
import az.restopia.notification.domain.mapper.NotificationMapper;
import az.restopia.notification.domain.response.NotificationResponse;
import az.restopia.notification.domain.response.NotificationSummaryResponse;
import az.restopia.notification.repository.NotificationRepository;
import az.restopia.notification.service.NotificationService;
import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override

    public List<NotificationSummaryResponse> getAllNotifications() {
        log.info("Getting all notifications");
        List<Notification> notifications = notificationRepository.findAllByDeleteStatusOrderByCreatedDateTimeDesc(DeleteStatus.NOT_DELETED);
        return notificationMapper.toSummaryResponseList(notifications);
    }

    @Override
    public NotificationResponse getNotificationById(Long id) {
        log.info("Getting notification by id: {}", id);
        Notification notification = notificationRepository.findByIdAndDeleteStatus(id, DeleteStatus.NOT_DELETED)
                .orElseThrow(() -> new RecordNotFoundException("Notification not found with id: " + id));
        return notificationMapper.toResponse(notification);
    }

    @Override
    @Transactional
    public NotificationResponse markAsRead(Long id) {
        log.info("Marking notification as read, id: {}", id);
        Notification notification = notificationRepository.findByIdAndDeleteStatus(id, DeleteStatus.NOT_DELETED)
                .orElseThrow(() -> new RecordNotFoundException("Notification not found with id: " + id));
        
        notification.setIsRead(true);
        Notification savedNotification = notificationRepository.save(notification);
        return notificationMapper.toResponse(savedNotification);
    }

    @Override
    @Transactional
    public void markAllAsRead() {
        log.info("Marking all notifications as read");
        notificationRepository.markAllAsRead(DeleteStatus.NOT_DELETED);
    }

    @Override
    @Transactional
    public void deleteAllNotifications() {
        log.info("Deleting all notifications");
        notificationRepository.deleteAllNotifications(DeleteStatus.DELETED, DeleteStatus.NOT_DELETED);
    }

    @Override
    public List<NotificationSummaryResponse> searchNotifications(String keyword) {
        log.info("Searching notifications with keyword: {}", keyword);
        List<Notification> notifications = notificationRepository.searchNotifications(keyword, DeleteStatus.NOT_DELETED);
        return notificationMapper.toSummaryResponseList(notifications);
    }

    @Override
    @Transactional
    public void deleteNotification(Long id) {
        log.info("Deleting notification with id: {}", id);
        Notification notification = notificationRepository.findByIdAndDeleteStatus(id, DeleteStatus.NOT_DELETED)
                .orElseThrow(() -> new RecordNotFoundException("Notification not found with id: " + id));
        
        notification.setDeleteStatus(DeleteStatus.DELETED);
        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationSummaryResponse> filterNotifications(String filterType, String filterValue) {
        log.info("Filtering notifications by type: {} and value: {}", filterType, filterValue);
        
        List<Notification> notifications;
        
        switch (filterType.toUpperCase()) {
            case "PRIORITY":
                NotificationPriority priority = NotificationPriority.valueOf(filterValue.toUpperCase());
                notifications = notificationRepository.findAllByPriorityAndDeleteStatusOrderByCreatedDateTimeDesc(priority, DeleteStatus.NOT_DELETED);
                break;
            case "NOTIFICATION_CATEGORY":
                NotificationCategory category = NotificationCategory.valueOf(filterValue.toUpperCase());
                notifications = notificationRepository.findAllByCategoryAndDeleteStatusOrderByCreatedDateTimeDesc(category, DeleteStatus.NOT_DELETED);
                break;
            case "FROM_WHO":
                notifications = notificationRepository.findAllByFromUserAndDeleteStatusOrderByCreatedDateTimeDesc(filterValue, DeleteStatus.NOT_DELETED);
                break;
            default:
                throw new IllegalArgumentException("Invalid filter type: " + filterType);
        }
        
        return notificationMapper.toSummaryResponseList(notifications);
    }
}