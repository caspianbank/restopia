package az.restopia.conversations.repository;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.conversations.domain.entity.Message;
import az.restopia.conversations.domain.enums.MessageType;
import az.restopia.conversations.domain.enums.MessageStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
    @Query("SELECT m FROM Message m WHERE m.conversation.id = :conversationId " +
           "AND m.conversation.tenantCode = :tenantCode " +
           "AND m.deleteStatus = :deleteStatus " +
           "ORDER BY m.createdDate ASC")
    Page<Message> findByConversationIdAndTenantCode(
        @Param("conversationId") Long conversationId,
        @Param("tenantCode") String tenantCode,
        @Param("deleteStatus") DeleteStatus deleteStatus,
        Pageable pageable
    );
    
    @Query("SELECT m FROM Message m WHERE m.conversation.tenantCode = :tenantCode " +
           "AND m.deleteStatus = :deleteStatus " +
           "AND m.status = :status")
    Page<Message> findByTenantCodeAndStatus(
        @Param("tenantCode") String tenantCode,
        @Param("status") MessageStatus status,
        @Param("deleteStatus") DeleteStatus deleteStatus,
        Pageable pageable
    );
    
    @Query("SELECT m FROM Message m WHERE m.conversation.tenantCode = :tenantCode " +
           "AND m.deleteStatus = :deleteStatus " +
           "AND m.messageType = :messageType")
    Page<Message> findByTenantCodeAndMessageType(
        @Param("tenantCode") String tenantCode,
        @Param("messageType") MessageType messageType,
        @Param("deleteStatus") DeleteStatus deleteStatus,
        Pageable pageable
    );
    
    Optional<Message> findByIdAndConversationIdAndConversationTenantCodeAndDeleteStatus(
        Long id, 
        Long conversationId, 
        String tenantCode, 
        DeleteStatus deleteStatus
    );
}
