package az.restopia.conversations.domain.response;

import az.restopia.conversations.domain.enums.MessageStatus;
import az.restopia.conversations.domain.enums.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageResponse {
    
    private Long id;
    private Long conversationId;
    private String sender;
    private String content;
    private MessageType messageType;
    private MessageStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}