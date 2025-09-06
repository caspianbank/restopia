package az.restopia.notification.controller;

import az.restopia.notification.domain.response.NotificationResponse;
import az.restopia.notification.domain.response.NotificationSummaryResponse;
import az.restopia.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationSummaryResponse>> getAllNotifications() {
        log.info("REST request to get all notifications");
        List<NotificationSummaryResponse> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id) {
        log.info("REST request to get notification by id: {}", id);
        NotificationResponse notification = notificationService.getNotificationById(id);
        return ResponseEntity.ok(notification);
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<NotificationResponse> markNotificationAsRead(@PathVariable Long id) {
        log.info("REST request to mark notification as read, id: {}", id);
        NotificationResponse notification = notificationService.markAsRead(id);
        return ResponseEntity.ok(notification);
    }

    @PatchMapping("/all/read")
    public ResponseEntity<Void> markAllNotificationsAsRead() {
        log.info("REST request to mark all notifications as read");
        notificationService.markAllAsRead();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllNotifications() {
        log.info("REST request to delete all notifications");
        notificationService.deleteAllNotifications();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<NotificationSummaryResponse>> searchNotifications(@RequestParam String q) {
        log.info("REST request to search notifications with keyword: {}", q);
        List<NotificationSummaryResponse> notifications = notificationService.searchNotifications(q);
        return ResponseEntity.ok(notifications);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        log.info("REST request to delete notification with id: {}", id);
        notificationService.deleteNotification(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<NotificationSummaryResponse>> filterNotifications(
            @RequestParam("filter-type") String filterType,
            @RequestParam("filter-value") String filterValue) {
        log.info("REST request to filter notifications by type: {} and value: {}", filterType, filterValue);
        List<NotificationSummaryResponse> notifications = notificationService.filterNotifications(filterType, filterValue);
        return ResponseEntity.ok(notifications);
    }
}