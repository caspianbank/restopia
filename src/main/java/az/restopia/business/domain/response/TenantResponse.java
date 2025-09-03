package az.restopia.business.domain.response;

import az.restopia.business.domain.enums.SubscriptionPlan;
import az.restopia.business.domain.enums.SubscriptionStatus;
import az.restopia.business.domain.enums.TenantSize;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
