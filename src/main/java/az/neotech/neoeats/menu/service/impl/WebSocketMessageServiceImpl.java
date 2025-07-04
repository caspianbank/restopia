package az.neotech.neoeats.menu.service.impl;

import az.neotech.neoeats.menu.domain.event.DocumentReadyNotificationEvent;
import az.neotech.neoeats.menu.service.WebSocketMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebSocketMessageServiceImpl implements WebSocketMessageService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void sendDocumentReadyNotification(String username, DocumentReadyNotificationEvent event) {
        simpMessagingTemplate.convertAndSendToUser(username, "/documents/ready", event);
        log.info("Sent document ready notification to user {}: {}", username, event);
    }
}
