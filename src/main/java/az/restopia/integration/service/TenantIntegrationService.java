package az.restopia.integration.service;

import az.restopia.integration.domain.enums.IntegrationStatus;
import az.restopia.integration.domain.request.TenantIntegrationRequest;
import az.restopia.integration.domain.response.TenantIntegrationResponse;

import java.util.List;

public interface TenantIntegrationService {
    
    TenantIntegrationResponse addIntegrationForTenant(String tenantCode, TenantIntegrationRequest request);
    
    TenantIntegrationResponse updateTenantIntegrationStatus(String tenantCode, String integrationCode, IntegrationStatus status);
    
    void deleteTenantIntegration(String tenantCode, String integrationCode);
    
    TenantIntegrationResponse getTenantIntegrationById(String tenantCode, String integrationCode);
    
    List<TenantIntegrationResponse> getTenantIntegrations(String tenantCode);
}