package az.restopia.menu.service;

import az.restopia.menu.domain.event.DocumentReadyNotificationEvent;

public interface WebSocketMessageService {

    void sendDocumentReadyNotification(String username, DocumentReadyNotificationEvent event);
}
