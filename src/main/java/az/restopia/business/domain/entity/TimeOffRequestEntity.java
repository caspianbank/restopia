package az.restopia.business.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.restopia.business.domain.enums.TimeOffStatus;
import az.restopia.commons.domain.enums.DeleteStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "tenant_time_off_requests")
@Getter
@Setter
public class TimeOffRequestEntity extends DetailedAudit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "tenant_code", nullable = false)
    private String tenantCode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private TenantEmployee employee;
    
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    
    @Column(name = "reason")
    private String reason;
    
    @Column(name = "description")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TimeOffStatus status;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delete_status", nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;
}
