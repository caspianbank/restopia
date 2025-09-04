package az.restopia.conversations.controller;

import az.restopia.conversations.domain.request.ConversationRequest;
import az.restopia.conversations.domain.request.MessageRequest;
import az.restopia.conversations.domain.response.ConversationResponse;
import az.restopia.conversations.domain.response.MessageResponse;
import az.restopia.conversations.service.ConversationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping
    public ResponseEntity<Page<ConversationResponse>> getAllConversations(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @RequestParam String participant,
            Pageable pageable) {
        log.info("GET /api/v1/conversations - tenant: {}, participant: {}", tenantCode, participant);
        Page<ConversationResponse> conversations = conversationService.getAllConversations(tenantCode, participant, pageable);
        return ResponseEntity.ok(conversations);
    }

    @GetMapping("/{id}/messages")
    public ResponseEntity<Page<MessageResponse>> getConversationMessages(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id,
            Pageable pageable) {
        log.info("GET /api/v1/conversations/{}/messages - tenant: {}", id, tenantCode);
        Page<MessageResponse> messages = conversationService.getConversationMessages(tenantCode, id, pageable);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/messages/filter")
    public ResponseEntity<Page<MessageResponse>> getFilteredMessages(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @RequestParam("filter-type") String filterType,
            @RequestParam("filter-value") String filterValue,
            Pageable pageable) {
        log.info("GET /api/v1/conversations/messages/filter - tenant: {}, filter-type: {}, filter-value: {}",
                tenantCode, filterType, filterValue);
        Page<MessageResponse> messages = conversationService.getFilteredMessages(tenantCode, filterType, filterValue, pageable);
        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<ConversationResponse> createConversation(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @Valid @RequestBody ConversationRequest request) {
        log.info("POST /api/v1/conversations - tenant: {}", tenantCode);
        ConversationResponse response = conversationService.createConversation(tenantCode, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConversation(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id) {
        log.info("DELETE /api/v1/conversations/{} - tenant: {}", id, tenantCode);
        conversationService.deleteConversation(tenantCode, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/messages")
    public ResponseEntity<MessageResponse> sendMessage(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id,
            @Valid @RequestBody MessageRequest request) {
        log.info("POST /api/v1/conversations/{}/messages - tenant: {}", id, tenantCode);
        MessageResponse response = conversationService.sendMessage(tenantCode, id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{conversationId}/messages/{messageId}")
    public ResponseEntity<MessageResponse> updateMessage(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long conversationId,
            @PathVariable Long messageId,
            @Valid @RequestBody MessageRequest request) {
        log.info("PUT /api/v1/conversations/{}/messages/{} - tenant: {}", conversationId, messageId, tenantCode);
        MessageResponse response = conversationService.updateMessage(tenantCode, conversationId, messageId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{conversationId}/messages/{messageId}")
    public ResponseEntity<Void> deleteMessage(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long conversationId,
            @PathVariable Long messageId) {
        log.info("DELETE /api/v1/conversations/{}/messages/{} - tenant: {}", conversationId, messageId, tenantCode);
        conversationService.deleteMessage(tenantCode, conversationId, messageId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/archive")
    public ResponseEntity<Void> archiveConversation(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id) {
        log.info("POST /api/v1/conversations/{}/archive - tenant: {}", id, tenantCode);
        conversationService.archiveConversation(tenantCode, id);
        return ResponseEntity.noContent().build();
    }
}