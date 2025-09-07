package az.restopia.notification.domain.response;

import az.restopia.notification.domain.enums.NotificationCategory;
import az.restopia.notification.domain.enums.NotificationPriority;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationSummaryResponse {

    private Long id;
    private String title;
    private String fromUser;
    private String toUser;
    private Boolean isRead;
    private NotificationPriority priority;
    private NotificationCategory category;
    private LocalDateTime createdDateTime;
}