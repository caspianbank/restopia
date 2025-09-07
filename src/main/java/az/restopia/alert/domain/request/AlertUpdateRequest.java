package az.restopia.alert.domain.request;

import az.restopia.alert.domain.enums.AlertPriority;
import az.restopia.alert.domain.enums.AlertStatus;
import az.restopia.alert.domain.enums.AlertType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertUpdateRequest {

    private String title;
    private String description;
    private AlertPriority priority;
    private AlertStatus status;
    private AlertType type;
    private String assignedTo;
    private String resolvedBy;
}
