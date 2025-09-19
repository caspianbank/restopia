package az.restopia.conversations.repository;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.conversations.domain.entity.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    
    @Query("SELECT c FROM Conversation c WHERE c.tenantCode = :tenantCode " +
           "AND c.deleteStatus = :deleteStatus " +
           "AND (c.participantOne = :participant OR c.participantTwo = :participant)")
    Page<Conversation> findByTenantCodeAndParticipant(
        @Param("tenantCode") String tenantCode,
        @Param("participant") String participant,
        @Param("deleteStatus") DeleteStatus deleteStatus,
        Pageable pageable
    );
    
    Optional<Conversation> findByIdAndTenantCodeAndDeleteStatus(
        Long id, 
        String tenantCode, 
        DeleteStatus deleteStatus
    );
}