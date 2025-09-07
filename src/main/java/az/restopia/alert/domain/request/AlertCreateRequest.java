package az.restopia.alert.domain.request;

import az.restopia.alert.domain.enums.AlertPriority;
import az.restopia.alert.domain.enums.AlertStatus;
import az.restopia.alert.domain.enums.AlertType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertCreateRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;

    @NotNull(message = "Priority cannot be null")
    private AlertPriority priority = AlertPriority.MEDIUM;

    private AlertStatus status = AlertStatus.ACTIVE;

    @NotNull(message = "Type cannot be null")
    private AlertType type = AlertType.GENERAL;

    private String createdBy;

    private String assignedTo;

    private String tenantCode;
}
