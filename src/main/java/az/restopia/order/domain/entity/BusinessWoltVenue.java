package az.restopia.order.domain.entity;

import az.neotech.commons.audit.DateAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "business_wolt_venue")
public class BusinessWoltVenue extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_code", nullable = false)
    private String tenantCode;

    @Column(name = "venue_id", nullable = false, unique = true)
    private String venueId;

    @Column(name = "is_active", nullable = false)
    private boolean active;
}
