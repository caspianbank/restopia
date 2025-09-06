package az.restopia.notification.domain.response;

import az.restopia.notification.domain.enums.NotificationCategory;
import az.restopia.notification.domain.enums.NotificationPriority;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationResponse {

    private Long id;
    private String title;
    private String body;
    private String fromUser;
    private String toUser;
    private String tenantCode;
    private Boolean isRead;
    private NotificationPriority priority;
    private NotificationCategory category;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
}
