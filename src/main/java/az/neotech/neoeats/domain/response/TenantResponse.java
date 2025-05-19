package az.neotech.neoeats.domain.response;

import java.time.LocalDateTime;

import az.neotech.neoeats.domain.enums.SubscriptionPlan;
import az.neotech.neoeats.domain.enums.SubscriptionStatus;
import az.neotech.neoeats.domain.enums.TenantSize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantResponse {

    private String tenantCode;
    private String name;
    private String description;
    private TenantSize tenantSize;
    private String logoUrl;
    private String contactEmail;
    private String contactPhone;
    private String address;
    private SubscriptionPlan subscriptionPlan;
    private SubscriptionStatus subscriptionStatus;
    private LocalDateTime contractStart;
    private LocalDateTime contractEnd;
    private String featureFlags;
}
