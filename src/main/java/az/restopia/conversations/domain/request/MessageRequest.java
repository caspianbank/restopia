package az.restopia.conversations.domain.request;

import az.restopia.conversations.domain.enums.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {
    
    @NotBlank
    private String sender;
    
    @NotBlank
    private String content;
    
    @NotNull
    private MessageType messageType;
}