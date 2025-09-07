package az.restopia.alert.domain.response;

import az.restopia.alert.domain.enums.AlertPriority;
import az.restopia.alert.domain.enums.AlertStatus;
import az.restopia.alert.domain.enums.AlertType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlertResponse {

    private Long id;
    private String title;
    private String description;
    private AlertPriority priority;
    private AlertStatus status;
    private AlertType type;
    private String createdBy;
    private String assignedTo;
    private String tenantCode;
    private LocalDateTime resolvedAt;
    private String resolvedBy;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
}