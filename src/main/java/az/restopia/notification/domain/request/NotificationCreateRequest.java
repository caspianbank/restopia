package az.restopia.notification.domain.request;

import az.restopia.notification.domain.enums.NotificationCategory;
import az.restopia.notification.domain.enums.NotificationPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationCreateRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String body;

    private String fromUser;

    @NotBlank(message = "To user cannot be blank")
    private String toUser;

    @NotBlank(message = "Tenant code cannot be blank")
    private String tenantCode;

    @NotNull(message = "Priority cannot be null")
    private NotificationPriority priority = NotificationPriority.MEDIUM;

    @NotNull(message = "Category cannot be null")
    private NotificationCategory category = NotificationCategory.GENERAL;
}