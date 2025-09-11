package az.restopia.integration.service;

import az.restopia.integration.domain.response.IntegrationResponse;

import java.util.List;

public interface IntegrationService {
    List<IntegrationResponse> getAllIntegrations(String tenantCode);

    IntegrationResponse getIntegrationByCode(String code, String tenantCode);
}
