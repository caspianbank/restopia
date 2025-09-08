package az.restopia.notification.service;

import az.restopia.notification.domain.response.NotificationResponse;
import az.restopia.notification.domain.response.NotificationSummaryResponse;

import java.util.List;

public interface NotificationService {

    List<NotificationSummaryResponse> getAllNotifications(String tenantCode);

    NotificationResponse getNotificationById(Long id, String tenantCode);

    Long getUnreadNotificationCount(String tenantCode);

    NotificationResponse markAsRead(Long id, String tenantCode);

    void markAllAsRead(String tenantCode);

    void deleteAllNotifications(String tenantCode);

    List<NotificationSummaryResponse> searchNotifications(String q, String tenantCode);

    void deleteNotification(String tenantCode, Long id);

    List<NotificationSummaryResponse> filterNotifications(String filterType, String filterValue, String tenantCode);
}