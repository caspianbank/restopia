package az.restopia.conversations.service.impl;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.conversations.domain.entity.Conversation;
import az.restopia.conversations.domain.entity.Message;
import az.restopia.conversations.domain.enums.MessageStatus;
import az.restopia.conversations.domain.enums.MessageType;
import az.restopia.conversations.domain.mapper.ConversationMapper;
import az.restopia.conversations.domain.mapper.MessageMapper;
import az.restopia.conversations.domain.request.ConversationRequest;
import az.restopia.conversations.domain.request.MessageRequest;
import az.restopia.conversations.domain.response.ConversationResponse;
import az.restopia.conversations.domain.response.MessageResponse;
import az.restopia.conversations.repository.ConversationRepository;
import az.restopia.conversations.repository.MessageRepository;
import az.restopia.conversations.service.ConversationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final ConversationMapper conversationMapper;
    private final MessageMapper messageMapper;

    @Override
    public Page<ConversationResponse> getAllConversations(String tenantCode, String participant, Pageable pageable) {
        log.info("Getting all conversations for tenant: {} and participant: {}", tenantCode, participant);
        Page<Conversation> conversations = conversationRepository
                .findByTenantCodeAndParticipant(tenantCode, participant, DeleteStatus.ACTIVE, pageable);
        return conversations.map(conversationMapper::toResponse);
    }

    @Override
    public Page<MessageResponse> getConversationMessages(String tenantCode, Long conversationId, Pageable pageable) {
        log.info("Getting messages for conversation: {} in tenant: {}", conversationId, tenantCode);
        Page<Message> messages = messageRepository
                .findByConversationIdAndTenantCode(conversationId, tenantCode, DeleteStatus.ACTIVE, pageable);
        return messages.map(messageMapper::toResponse);
    }

    @Override
    public Page<MessageResponse> getFilteredMessages(String tenantCode, String filterType, String filterValue, Pageable pageable) {
        log.info("Getting filtered messages for tenant: {} with filter type: {} and value: {}",
                tenantCode, filterType, filterValue);

        Page<Message> messages;
        switch (filterType.toUpperCase()) {
            case "STATUS":
                MessageStatus status = MessageStatus.valueOf(filterValue.toUpperCase());
                messages = messageRepository.findByTenantCodeAndStatus(tenantCode, status, DeleteStatus.ACTIVE, pageable);
                break;
            case "MESSAGE_TYPE":
                MessageType messageType = MessageType.valueOf(filterValue.toUpperCase());
                messages = messageRepository.findByTenantCodeAndMessageType(tenantCode, messageType, DeleteStatus.ACTIVE, pageable);
                break;
            default:
                throw new IllegalArgumentException("Invalid filter type: " + filterType);
        }

        return messages.map(messageMapper::toResponse);
    }

    @Override
    @Transactional
    public ConversationResponse createConversation(String tenantCode, ConversationRequest request) {
        log.info("Creating new conversation for tenant: {}", tenantCode);
        Conversation conversation = conversationMapper.toEntity(request);
        conversation.setTenantCode(tenantCode);
        conversation.setDeleteStatus(DeleteStatus.ACTIVE);

        Conversation savedConversation = conversationRepository.save(conversation);
        return conversationMapper.toResponse(savedConversation);
    }

    @Override
    @Transactional
    public void deleteConversation(String tenantCode, Long id) {
        log.info("Deleting conversation: {} for tenant: {}", id, tenantCode);
        Conversation conversation = conversationRepository
                .findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Conversation not found"));

        conversation.setDeleteStatus(DeleteStatus.DELETED);
        conversationRepository.save(conversation);
    }

    @Override
    @Transactional
    public MessageResponse sendMessage(String tenantCode, Long conversationId, MessageRequest request) {
        log.info("Sending message to conversation: {} for tenant: {}", conversationId, tenantCode);
        Conversation conversation = conversationRepository
                .findByIdAndTenantCodeAndDeleteStatus(conversationId, tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Conversation not found"));

        Message message = messageMapper.toEntity(request);
        message.setConversation(conversation);
        message.setStatus(MessageStatus.SENT);
        message.setDeleteStatus(DeleteStatus.ACTIVE);

        Message savedMessage = messageRepository.save(message);

        conversation.setLastMessageAt(LocalDateTime.now());
        conversationRepository.save(conversation);

        return messageMapper.toResponse(savedMessage);
    }

    @Override
    @Transactional
    public MessageResponse updateMessage(String tenantCode, Long conversationId, Long messageId, MessageRequest request) {
        log.info("Updating message: {} in conversation: {} for tenant: {}", messageId, conversationId, tenantCode);
        Message message = messageRepository
                .findByIdAndConversationIdAndConversationTenantCodeAndDeleteStatus(
                        messageId, conversationId, tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Message not found"));

        messageMapper.updateEntity(request, message);
        Message savedMessage = messageRepository.save(message);

        return messageMapper.toResponse(savedMessage);
    }

    @Override
    @Transactional
    public void deleteMessage(String tenantCode, Long conversationId, Long messageId) {
        log.info("Deleting message: {} in conversation: {} for tenant: {}", messageId, conversationId, tenantCode);
        Message message = messageRepository
                .findByIdAndConversationIdAndConversationTenantCodeAndDeleteStatus(
                        messageId, conversationId, tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Message not found"));

        message.setDeleteStatus(DeleteStatus.DELETED);
        messageRepository.save(message);
    }

    @Override
    @Transactional
    public void archiveConversation(String tenantCode, Long id) {
        log.info("Archiving conversation: {} for tenant: {}", id, tenantCode);
        Conversation conversation = conversationRepository
                .findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Conversation not found"));

        conversation.setArchived(true);
        conversationRepository.save(conversation);
    }
}