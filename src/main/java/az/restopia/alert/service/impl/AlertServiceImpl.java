package az.restopia.alert.service.impl;

import az.restopia.alert.domain.entity.Alert;
import az.restopia.alert.domain.enums.AlertPriority;
import az.restopia.alert.domain.enums.AlertStatus;
import az.restopia.alert.domain.mapper.AlertMapper;
import az.restopia.alert.domain.request.AlertCreateRequest;
import az.restopia.alert.domain.request.AlertUpdateRequest;
import az.restopia.alert.domain.response.AlertResponse;
import az.restopia.alert.repository.AlertRepository;
import az.restopia.alert.service.AlertService;
import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.notification.domain.entity.Notification;
import az.restopia.notification.domain.enums.NotificationCategory;
import az.restopia.notification.domain.enums.NotificationPriority;
import az.restopia.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final AlertMapper alertMapper;
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<AlertResponse> getAllAlerts(String tenantCode, Pageable pageable) {
        log.debug("Fetching all alerts for tenant: {}", tenantCode);
        Page<Alert> alerts = alertRepository.findByTenantCodeAndDeleteStatus(tenantCode, DeleteStatus.NOT_DELETED, pageable);
        return alerts.map(alertMapper::toResponse);
    }

    @Override
    public AlertResponse createAlert(AlertCreateRequest request) {
        log.debug("Creating new alert for tenant: {}", request.getTenantCode());
        
        Alert alert = alertMapper.toEntity(request);
        alert.setCreatedDateTime(LocalDateTime.now());
        alert.setModifiedDateTime(LocalDateTime.now());
        
        Alert savedAlert = alertRepository.save(alert);
        
        // Send notification when alert is created
        sendAlertCreatedNotification(savedAlert);
        
        return alertMapper.toResponse(savedAlert);
    }

    @Override
    public AlertResponse updateAlert(Long id, String tenantCode, AlertUpdateRequest request) {
        log.debug("Updating alert with id: {} for tenant: {}", id, tenantCode);
        
        Alert existingAlert = alertRepository.findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.NOT_DELETED)
                .orElseThrow(() -> new RecordNotFoundException("Alert not found with id: " + id));

        AlertPriority oldPriority = existingAlert.getPriority();
        
        // Update fields
        if (request.getTitle() != null) {
            existingAlert.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            existingAlert.setDescription(request.getDescription());
        }
        if (request.getPriority() != null) {
            existingAlert.setPriority(request.getPriority());
        }
        if (request.getStatus() != null) {
            existingAlert.setStatus(request.getStatus());
            if (request.getStatus() == AlertStatus.RESOLVED) {
                existingAlert.setResolvedAt(LocalDateTime.now());
                existingAlert.setResolvedBy(request.getResolvedBy());
            }
        }
        if (request.getType() != null) {
            existingAlert.setType(request.getType());
        }
        if (request.getAssignedTo() != null) {
            existingAlert.setAssignedTo(request.getAssignedTo());
        }
        
        existingAlert.setModifiedDateTime(LocalDateTime.now());
        
        Alert savedAlert = alertRepository.save(existingAlert);
        
        // Send notification if priority changed
        if (request.getPriority() != null && !oldPriority.equals(request.getPriority())) {
            sendAlertPriorityUpdatedNotification(savedAlert, oldPriority);
        }
        
        return alertMapper.toResponse(savedAlert);
    }

    @Override
    public void deleteAlert(Long id, String tenantCode) {
        log.debug("Soft deleting alert with id: {} for tenant: {}", id, tenantCode);
        
        Alert alert = alertRepository.findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.NOT_DELETED)
                .orElseThrow(() -> new RecordNotFoundException("Alert not found with id: " + id));
        
        alert.setDeleteStatus(DeleteStatus.DELETED);
        alert.setModifiedDateTime(LocalDateTime.now());
        
        alertRepository.save(alert);
        
        // Send notification when alert is deleted
        sendAlertDeletedNotification(alert);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlertResponse> searchAlerts(String q, String tenantCode, Pageable pageable) {
        log.debug("Searching alerts with keyword: {} for tenant: {}", q, tenantCode);
        Page<Alert> alerts = alertRepository.searchAlerts(tenantCode, q, pageable);
        return alerts.map(alertMapper::toResponse);
    }

    private void sendAlertCreatedNotification(Alert alert) {
        try {
            Notification notification = createNotification(
                "New Alert Created: " + alert.getTitle(),
                String.format("A new %s priority alert has been created: %s", 
                    alert.getPriority().name().toLowerCase(), alert.getDescription()),
                "SYSTEM",
                alert.getAssignedTo() != null ? alert.getAssignedTo() : alert.getCreatedBy(),
                alert.getTenantCode(),
                mapAlertPriorityToNotificationPriority(alert.getPriority()),
                NotificationCategory.ALERT
            );
            
            notificationRepository.save(notification);
            log.debug("Alert created notification sent for alert id: {}", alert.getId());
        } catch (Exception e) {
            log.error("Failed to send alert created notification for alert id: {}", alert.getId(), e);
        }
    }

    private void sendAlertPriorityUpdatedNotification(Alert alert, AlertPriority oldPriority) {
        try {
            Notification notification = createNotification(
                "Alert Priority Updated: " + alert.getTitle(),
                String.format("Alert priority changed from %s to %s for: %s", 
                    oldPriority.name().toLowerCase(), 
                    alert.getPriority().name().toLowerCase(), 
                    alert.getDescription()),
                "SYSTEM",
                alert.getAssignedTo() != null ? alert.getAssignedTo() : alert.getCreatedBy(),
                alert.getTenantCode(),
                mapAlertPriorityToNotificationPriority(alert.getPriority()),
                NotificationCategory.UPDATE
            );
            
            notificationRepository.save(notification);
            log.debug("Alert priority updated notification sent for alert id: {}", alert.getId());
        } catch (Exception e) {
            log.error("Failed to send alert priority updated notification for alert id: {}", alert.getId(), e);
        }
    }

    private void sendAlertDeletedNotification(Alert alert) {
        try {
            Notification notification = createNotification(
                "Alert Deleted: " + alert.getTitle(),
                String.format("Alert has been deleted or marked as false alert: %s", alert.getDescription()),
                "SYSTEM",
                alert.getAssignedTo() != null ? alert.getAssignedTo() : alert.getCreatedBy(),
                alert.getTenantCode(),
                NotificationPriority.MEDIUM,
                NotificationCategory.ALERT
            );
            
            notificationRepository.save(notification);
            log.debug("Alert deleted notification sent for alert id: {}", alert.getId());
        } catch (Exception e) {
            log.error("Failed to send alert deleted notification for alert id: {}", alert.getId(), e);
        }
    }

    private Notification createNotification(String title, String body, String fromUser, 
                                          String toUser, String tenantCode, 
                                          NotificationPriority priority, NotificationCategory category) {
        Notification notification = new Notification();
        notification.setTitle(title);
        notification.setBody(body);
        notification.setFromUser(fromUser);
        notification.setToUser(toUser);
        notification.setTenantCode(tenantCode);
        notification.setPriority(priority);
        notification.setCategory(category);
        notification.setIsRead(false);
        notification.setDeleteStatus(DeleteStatus.NOT_DELETED);
        notification.setCreatedDateTime(LocalDateTime.now());
        notification.setModifiedDateTime(LocalDateTime.now());
        
        return notification;
    }

    private NotificationPriority mapAlertPriorityToNotificationPriority(AlertPriority alertPriority) {
        switch (alertPriority) {
            case LOW:
                return NotificationPriority.LOW;
            case MEDIUM:
                return NotificationPriority.MEDIUM;
            case HIGH:
                return NotificationPriority.HIGH;
            case CRITICAL:
            case URGENT:
                return NotificationPriority.URGENT;
            default:
                return NotificationPriority.MEDIUM;
        }
    }
}