package az.restopia.integration.service.impl;

import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.integration.domain.entity.Integration;
import az.restopia.integration.domain.entity.TenantIntegration;
import az.restopia.integration.domain.enums.IntegrationStatus;
import az.restopia.integration.domain.mapper.IntegrationMapper;
import az.restopia.integration.domain.request.TenantIntegrationRequest;
import az.restopia.integration.domain.response.TenantIntegrationResponse;
import az.restopia.integration.repository.IntegrationRepository;
import az.restopia.integration.repository.TenantIntegrationRepository;
import az.restopia.integration.service.TenantIntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TenantIntegrationServiceImpl implements TenantIntegrationService {

    private final TenantIntegrationRepository tenantIntegrationRepository;
    private final IntegrationRepository integrationRepository;
    private final IntegrationMapper tenantIntegrationMapper;

    @Override
    @Transactional
    public TenantIntegrationResponse addIntegrationForTenant(String tenantCode, TenantIntegrationRequest request) {
        log.debug("Adding integration {} for tenant: {}", request.getIntegrationCode(), tenantCode);

        Integration integration = integrationRepository.findByCode(request.getIntegrationCode())
                .orElseThrow(() -> new RecordNotFoundException("Integration with code " + request.getIntegrationCode() + " not found"));

        if (tenantIntegrationRepository.existsByTenantCodeAndIntegration(tenantCode, integration)) {
            throw new IllegalArgumentException("Integration already exists for tenant: " + tenantCode);
        }

        TenantIntegration tenantIntegration = tenantIntegrationMapper.toEntity(request);
        tenantIntegration.setTenantCode(tenantCode);
        tenantIntegration.setIntegration(integration);
        tenantIntegration.setStatus(IntegrationStatus.ENABLED);
        
        TenantIntegration savedEntity = tenantIntegrationRepository.save(tenantIntegration);
        
        log.info("Successfully added integration {} for tenant: {}", request.getIntegrationCode(), tenantCode);
        return tenantIntegrationMapper.toResponse(savedEntity);
    }

    @Override
    @Transactional
    public TenantIntegrationResponse updateTenantIntegrationStatus(String tenantCode, String integrationCode, IntegrationStatus status) {
        log.debug("Updating tenant integration status: {} for tenant: {}, code: {}", status, tenantCode, integrationCode);

        Integration integration = integrationRepository.findByCode(integrationCode)
                .orElseThrow(() -> new RecordNotFoundException("Integration with code " + integrationCode + " not found"));

        TenantIntegration tenantIntegration = tenantIntegrationRepository.findByIntegrationAndTenantCode(integration, tenantCode)
                .orElseThrow(() -> new RecordNotFoundException("Tenant integration not found with id: " + integrationCode));
        tenantIntegration.setStatus(status);
        TenantIntegration savedEntity = tenantIntegrationRepository.save(tenantIntegration);
        
        log.info("Successfully updated tenant integration status to {} for tenant: {}, code: {}", status, tenantCode, integrationCode);
        return tenantIntegrationMapper.toResponse(savedEntity);
    }

    @Override
    @Transactional
    public void deleteTenantIntegration(String tenantCode, String integrationCode) {
        log.debug("Deleting tenant integration for tenant: {}, code: {}", tenantCode, integrationCode);

        Integration integration = integrationRepository.findByCode(integrationCode)
                .orElseThrow(() -> new RecordNotFoundException("Integration with code " + integrationCode + " not found"));

        TenantIntegration tenantIntegration = tenantIntegrationRepository.findByIntegrationAndTenantCode(integration, tenantCode)
                .orElseThrow(() -> new RecordNotFoundException("Tenant integration not found with code: " + integrationCode));
        
        tenantIntegrationRepository.delete(tenantIntegration);
        
        log.info("Successfully deleted tenant integration for tenant: {}, code: {}", tenantCode, integrationCode);
    }

    @Override
    public TenantIntegrationResponse getTenantIntegrationById(String tenantCode, String integrationCode) {
        log.debug("Getting tenant integration for tenant: {}, code: {}", tenantCode, integrationCode);

        Integration integration = integrationRepository.findByCode(integrationCode)
                .orElseThrow(() -> new RecordNotFoundException("Integration with code " + integrationCode + " not found"));

        TenantIntegration tenantIntegration = tenantIntegrationRepository.findByIntegrationAndTenantCode(integration, tenantCode)
                .orElseThrow(() -> new RecordNotFoundException("Tenant integration not found with co: " + integrationCode));
        
        return tenantIntegrationMapper.toResponse(tenantIntegration);
    }

    @Override
    public List<TenantIntegrationResponse> getTenantIntegrations(String tenantCode) {
        log.debug("Getting all tenant integrations for tenant: {}", tenantCode);
        
        List<TenantIntegration> tenantIntegrations = tenantIntegrationRepository.findByTenantCode(tenantCode);
        return tenantIntegrations.stream()
                .map(tenantIntegrationMapper::toResponse)
                .toList();
    }
}