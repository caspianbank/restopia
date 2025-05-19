package az.neotech.neoeats.domain.request;

import java.time.LocalDateTime;

import az.neotech.neoeats.domain.enums.SubscriptionPlan;
import az.neotech.neoeats.domain.enums.SubscriptionStatus;
import az.neotech.neoeats.domain.enums.TenantSize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantRequest {

    @NotBlank
    @Size(max = 50)
    private String tenantCode;

    @NotBlank
    @Size(max = 100)
    private String name;

    private String description;

    @NotNull
    private TenantSize tenantSize;

    @Size(max = 255)
    private String logoUrl;

    @Email
    @Size(max = 100)
    private String contactEmail;

    @Size(max = 20)
    private String contactPhone;

    @Size(max = 255)
    private String address;

    @NotNull
    private SubscriptionPlan subscriptionPlan;

    @NotNull
    private SubscriptionStatus subscriptionStatus;

    private LocalDateTime contractStart;

    private LocalDateTime contractEnd;

    private String featureFlags; // JSON string
}
