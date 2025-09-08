package az.restopia.notification.service.impl;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.notification.domain.entity.Notification;
import az.restopia.notification.domain.enums.NotificationCategory;
import az.restopia.notification.domain.enums.NotificationPriority;
import az.restopia.notification.domain.mapper.NotificationMapper;
import az.restopia.notification.domain.response.NotificationResponse;
import az.restopia.notification.domain.response.NotificationSummaryResponse;
import az.restopia.notification.repository.NotificationRepository;
import az.restopia.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    @Transactional(readOnly = true)
    public List<NotificationSummaryResponse> getAllNotifications(String tenantCode) {
        log.debug("Fetching all notifications for tenant: {}", tenantCode);
        List<Notification> notifications = notificationRepository.findByTenantCodeAndDeleteStatus(
                tenantCode, DeleteStatus.NOT_DELETED);
        return notificationMapper.toSummaryResponseList(notifications);
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationResponse getNotificationById(Long id, String tenantCode) {
        log.debug("Fetching notification with id: {} for tenant: {}", id, tenantCode);
        Notification notification = notificationRepository
                .findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.NOT_DELETED)
                .orElseThrow(() -> new RecordNotFoundException("Notification not found with id: " + id));
        return notificationMapper.toResponse(notification);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getUnreadNotificationCount(String tenantCode) {
        log.debug("Fetching unread notification count for tenant: {}", tenantCode);
        return notificationRepository.countUnreadNotificationsByTenantCode(tenantCode);
    }

    @Override
    public NotificationResponse markAsRead(Long id, String tenantCode) {
        log.debug("Marking notification as read with id: {} for tenant: {}", id, tenantCode);
        Notification notification = notificationRepository
                .findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.NOT_DELETED)
                .orElseThrow(() -> new RecordNotFoundException("Notification not found with id: " + id));

        notification.setIsRead(true);
        notification.setModifiedDateTime(LocalDateTime.now());
        Notification savedNotification = notificationRepository.save(notification);
        return notificationMapper.toResponse(savedNotification);
    }

    @Override
    public void markAllAsRead(String tenantCode) {
        log.debug("Marking all notifications as read for tenant: {}", tenantCode);
        notificationRepository.markAllAsReadByTenantCode(tenantCode);
    }

    @Override
    public void deleteAllNotifications(String tenantCode) {
        log.debug("Deleting all notifications for tenant: {}", tenantCode);
        notificationRepository.softDeleteAllByTenantCode(tenantCode);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationSummaryResponse> searchNotifications(String q, String tenantCode) {
        log.debug("Searching notifications for tenant: {} with keyword: {}", tenantCode, q);
        List<Notification> notifications = notificationRepository.searchNotifications(tenantCode, q);
        return notificationMapper.toSummaryResponseList(notifications);
    }

    @Override
    public void deleteNotification(String tenantCode, Long id) {
        log.debug("Soft deleting notification with id: {} for tenant: {}", id, tenantCode);
        Notification notification = notificationRepository
                .findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.NOT_DELETED)
                .orElseThrow(() -> new RecordNotFoundException("Notification not found with id: " + id));

        notification.setDeleteStatus(DeleteStatus.DELETED);
        notification.setModifiedDateTime(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationSummaryResponse> filterNotifications(String filterType, String filterValue, String tenantCode) {
        log.debug("Filtering notifications for tenant: {} with type: {} and value: {}",
                tenantCode, filterType, filterValue);

        List<Notification> notifications;

        switch (filterType.toUpperCase()) {
            case "PRIORITY":
                NotificationPriority priority = NotificationPriority.valueOf(filterValue.toUpperCase());
                notifications = notificationRepository.findByTenantCodeAndPriorityAndDeleteStatus(
                        tenantCode, priority, DeleteStatus.NOT_DELETED);
                break;
            case "NOTIFICATION_CATEGORY":
            case "CATEGORY":
                NotificationCategory category = NotificationCategory.valueOf(filterValue.toUpperCase());
                notifications = notificationRepository.findByTenantCodeAndCategoryAndDeleteStatus(
                        tenantCode, category, DeleteStatus.NOT_DELETED);
                break;
            case "FROM":
            case "FROM_USER":
                notifications = notificationRepository.findByTenantCodeAndFromUserAndDeleteStatus(
                        tenantCode, filterValue, DeleteStatus.NOT_DELETED);
                break;
            default:
                throw new IllegalArgumentException("Invalid filter type: " + filterType);
        }

        return notificationMapper.toSummaryResponseList(notifications);
    }
}