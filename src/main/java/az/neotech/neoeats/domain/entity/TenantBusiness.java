package az.neotech.neoeats.domain.entity;

import az.neotech.neoeats.domain.enums.BusinessType;
import az.neotech.neoeats.domain.enums.DeleteStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tenant_businesses")
public class TenantBusiness /*extends DateAudit*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = 50)
    private String tenantCode;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "business_type", length = 50, nullable = false)
    private BusinessType businessType;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delete_status", nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;
}
