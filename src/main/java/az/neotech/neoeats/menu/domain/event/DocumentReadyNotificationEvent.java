package az.neotech.neoeats.menu.domain.event;

public record DocumentReadyNotificationEvent(
        String documentName,
        String documentUrl
) {
}
