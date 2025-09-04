package az.restopia.conversations.domain.mapper;

import az.restopia.conversations.domain.entity.Message;
import az.restopia.conversations.domain.request.MessageRequest;
import az.restopia.conversations.domain.response.MessageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "conversation", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Message toEntity(MessageRequest request);
    
    @Mapping(source = "conversation.id", target = "conversationId")
    MessageResponse toResponse(Message message);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "conversation", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    void updateEntity(MessageRequest request, @MappingTarget Message message);
}