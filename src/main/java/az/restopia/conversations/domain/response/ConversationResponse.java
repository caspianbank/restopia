package az.restopia.conversations.domain.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ConversationResponse {
    
    private Long id;
    private String tenantCode;
    private String participantOne;
    private String participantTwo;
    private String title;
    private boolean isArchived;
    private LocalDateTime lastMessageAt;
    private List<MessageResponse> messages;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}