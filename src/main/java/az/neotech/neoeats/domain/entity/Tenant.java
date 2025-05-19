package az.neotech.neoeats.domain.entity;

import java.time.LocalDateTime;

import az.neotech.neoeats.domain.enums.DeleteStatus;
import az.neotech.neoeats.domain.enums.SubscriptionPlan;
import az.neotech.neoeats.domain.enums.SubscriptionStatus;
import az.neotech.neoeats.domain.enums.TenantSize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tenants")
public class Tenant /*extends DateAudit*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = 50, unique = true)
    private String tenantCode;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "tenant_size", length = 20, nullable = false)
    private TenantSize tenantSize = TenantSize.SMALL;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    @Column(name = "contact_phone", length = 20)
    private String contactPhone;

    @Column(name = "address", length = 255)
    private String address;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_plan", length = 50)
    private SubscriptionPlan subscriptionPlan = SubscriptionPlan.BASIC;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_status", length = 20)
    private SubscriptionStatus subscriptionStatus = SubscriptionStatus.ACTIVE;

    @Builder.Default
    @Column(name = "contract_start")
    private LocalDateTime contractStart = LocalDateTime.now();

    @Column(name = "contract_end")
    private LocalDateTime contractEnd;

    @Builder.Default
    @Column(name = "feature_flags", columnDefinition = "jsonb")
    private String featureFlags = "{}";

    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "delete_status", nullable = false)
    private DeleteStatus deleteStatus = DeleteStatus.ACTIVE;
}
