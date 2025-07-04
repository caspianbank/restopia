package az.neotech.neoeats.menu.service;

import az.neotech.neoeats.menu.domain.event.DocumentReadyNotificationEvent;

public interface WebSocketMessageService {

    void sendDocumentReadyNotification(String username, DocumentReadyNotificationEvent event);
}
