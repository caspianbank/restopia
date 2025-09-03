package az.restopia.menu.domain.event;

public record DocumentReadyNotificationEvent(
        String documentName,
        String documentUrl
) {
}
