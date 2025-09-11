package az.restopia.integration.domain.entity;

import az.neotech.commons.audit.DateAudit;
import az.restopia.commons.domain.constants.ColumnLengthConstants;
import az.restopia.integration.domain.enums.IntegrationEnvironment;
import az.restopia.integration.domain.enums.IntegrationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(
        name = "tenant_integrations",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant_code", "integration_id"})
)
public class TenantIntegration extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "integration_id", nullable = false)
    private Integration integration;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private IntegrationStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "environment", nullable = false)
    private IntegrationEnvironment environment;

    @Column(name = "country_code")
    private String countryCode;

}
