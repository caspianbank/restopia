package az.restopia.integration.domain.request;

import az.restopia.integration.domain.enums.IntegrationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantIntegrationRequest {
    
    @NotNull(message = "Integration code is required")
    private String integrationCode;
    
    @NotNull(message = "Status is required")
    private IntegrationStatus status;
}