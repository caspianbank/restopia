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
    public ResponseEntity<List<NotificationSummaryResponse>> getAllNotifications(
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("REST request to get all notifications for tenant: {}", tenantCode);
        List<NotificationSummaryResponse> notifications = notificationService.getAllNotifications(tenantCode);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(
            @PathVariable Long id,
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("REST request to get notification by id: {} for tenant: {}", id, tenantCode);
        NotificationResponse notification = notificationService.getNotificationById(id, tenantCode);
        return ResponseEntity.ok(notification);
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<NotificationResponse> markNotificationAsRead(
            @PathVariable Long id,
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("REST request to mark notification as read, id: {} for tenant: {}", id, tenantCode);
        NotificationResponse notification = notificationService.markAsRead(id, tenantCode);
        return ResponseEntity.ok(notification);
    }

    @PatchMapping("/all/read")
    public ResponseEntity<Void> markAllNotificationsAsRead(
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("REST request to mark all notifications as read for tenant: {}", tenantCode);
        notificationService.markAllAsRead(tenantCode);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllNotifications(
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("REST request to delete all notifications for tenant: {}", tenantCode);
        notificationService.deleteAllNotifications(tenantCode);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<NotificationSummaryResponse>> searchNotifications(
            @RequestParam String q,
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("REST request to search notifications with keyword: {} for tenant: {}", q, tenantCode);
        List<NotificationSummaryResponse> notifications = notificationService.searchNotifications(q, tenantCode);
        return ResponseEntity.ok(notifications);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(
            @PathVariable Long id,
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("REST request to delete notification with id: {} for tenant: {}", id, tenantCode);
        notificationService.deleteNotification(tenantCode, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<NotificationSummaryResponse>> filterNotifications(
            @RequestParam("filter-type") String filterType,
            @RequestParam("filter-value") String filterValue,
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("REST request to filter notifications by type: {} and value: {} for tenant: {}", filterType, filterValue, tenantCode);
        List<NotificationSummaryResponse> notifications = notificationService.filterNotifications(filterType, filterValue, tenantCode);
        return ResponseEntity.ok(notifications);
    }
}