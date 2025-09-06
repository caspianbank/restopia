package az.restopia.notification.service;

import az.restopia.notification.domain.response.NotificationResponse;
import az.restopia.notification.domain.response.NotificationSummaryResponse;

import java.util.List;

public interface NotificationService {

    List<NotificationSummaryResponse> getAllNotifications();

    NotificationResponse getNotificationById(Long id);

    NotificationResponse markAsRead(Long id);

    void markAllAsRead();

    void deleteAllNotifications();

    List<NotificationSummaryResponse> searchNotifications(String keyword);

    void deleteNotification(Long id);

    List<NotificationSummaryResponse> filterNotifications(String filterType, String filterValue);
}