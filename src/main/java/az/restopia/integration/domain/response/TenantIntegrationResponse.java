package az.restopia.integration.domain.response;

import az.restopia.integration.domain.enums.IntegrationEnvironment;
import az.restopia.integration.domain.enums.IntegrationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TenantIntegrationResponse {
    
    private Long id;
    private String integrationCode;
    private String tenantCode;
    private IntegrationResponse integration;
    private IntegrationStatus status;
    private IntegrationEnvironment environment;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
}