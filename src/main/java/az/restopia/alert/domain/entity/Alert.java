package az.restopia.alert.domain.entity;

import az.neotech.commons.audit.DateAudit;
import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.alert.domain.enums.AlertPriority;
import az.restopia.alert.domain.enums.AlertStatus;
import az.restopia.alert.domain.enums.AlertType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Getter
@Setter
public class Alert extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertPriority priority = AlertPriority.MEDIUM;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertStatus status = AlertStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertType type = AlertType.GENERAL;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "tenant_code")
    private String tenantCode;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @Column(name = "resolved_by")
    private String resolvedBy;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus = DeleteStatus.NOT_DELETED;

    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;

    @Column(name = "modified_date_time")
    private LocalDateTime modifiedDateTime;
}