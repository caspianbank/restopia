package az.restopia.conversations.service;

import az.restopia.conversations.domain.request.ConversationRequest;
import az.restopia.conversations.domain.request.MessageRequest;
import az.restopia.conversations.domain.response.ConversationResponse;
import az.restopia.conversations.domain.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConversationService {
    
    Page<ConversationResponse> getAllConversations(String tenantCode, String participant, Pageable pageable);
    
    Page<MessageResponse> getConversationMessages(String tenantCode, Long conversationId, Pageable pageable);
    
    Page<MessageResponse> getFilteredMessages(String tenantCode, String filterType, String filterValue, Pageable pageable);
    
    ConversationResponse createConversation(String tenantCode, ConversationRequest request);
    
    void deleteConversation(String tenantCode, Long id);
    
    MessageResponse sendMessage(String tenantCode, Long conversationId, MessageRequest request);
    
    MessageResponse updateMessage(String tenantCode, Long conversationId, Long messageId, MessageRequest request);
    
    void deleteMessage(String tenantCode, Long conversationId, Long messageId);
    
    void archiveConversation(String tenantCode, Long id);
}