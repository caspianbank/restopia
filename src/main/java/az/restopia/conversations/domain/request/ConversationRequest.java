package az.restopia.conversations.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationRequest {
    
    @NotBlank
    private String participantOne;
    
    @NotBlank
    private String participantTwo;
    
    private String title;
}