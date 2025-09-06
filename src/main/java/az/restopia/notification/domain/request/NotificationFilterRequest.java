package az.restopia.notification.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationFilterRequest {

    private String filterType;
    private String filterValue;
}