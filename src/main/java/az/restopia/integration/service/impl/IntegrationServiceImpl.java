package az.restopia.integration.service.impl;

import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.integration.domain.mapper.IntegrationMapper;
import az.restopia.integration.domain.response.IntegrationResponse;
import az.restopia.integration.repository.IntegrationRepository;
import az.restopia.integration.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IntegrationServiceImpl implements IntegrationService {

    private final IntegrationRepository integrationRepository;
    private final IntegrationMapper integrationMapper;

    @Override
    public List<IntegrationResponse> getAllIntegrations(String tenantCode) {
        log.debug("Getting all integrations for tenant: {}", tenantCode);
        // todo: get tenantCode in here, find that tenant country - then use that country in here
        return integrationRepository.findAll().stream()
                .map(integrationMapper::toResponse)
                .toList();
    }

    @Override
    public IntegrationResponse getIntegrationByCode(String code, String tenantCode) {
        log.debug("Getting integration by code: {} for tenant: {}", code, tenantCode);
        return integrationRepository.findByCode(code)
                .map(integrationMapper::toResponse)
                .orElseThrow(() -> new RecordNotFoundException("Integration not found with code: " + code));
    }

}
