package az.restopia.conversations.domain.mapper;

import az.restopia.conversations.domain.entity.Conversation;
import az.restopia.conversations.domain.request.ConversationRequest;
import az.restopia.conversations.domain.response.ConversationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MessageMapper.class})
public interface ConversationMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantCode", ignore = true)
    @Mapping(target = "isArchived", ignore = true)
    @Mapping(target = "lastMessageAt", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "deleteStatus", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Conversation toEntity(ConversationRequest request);
    
    ConversationResponse toResponse(Conversation conversation);
}
