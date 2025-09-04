package az.restopia.conversations.domain.entity;

import az.neotech.commons.audit.DateAudit;
import az.restopia.commons.domain.enums.DeleteStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "conversations")
@Getter
@Setter
public class Conversation extends DateAudit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tenant_code", nullable = false)
    private String tenantCode;
    
    @Column(name = "participant_one", nullable = false)
    private String participantOne;
    
    @Column(name = "participant_two", nullable = false)
    private String participantTwo;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "is_archived")
    private boolean isArchived = false;
    
    @Column(name = "last_message_at")
    private LocalDateTime lastMessageAt;
    
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delete_status", nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;
}