package az.restopia.business.service.impl;

import az.restopia.business.domain.entity.Tenant;
import az.restopia.business.domain.mapper.TenantMapper;
import az.restopia.business.domain.request.TenantRequest;
import az.restopia.business.domain.response.TenantResponse;
import az.restopia.business.repository.TenantRepository;
import az.restopia.business.service.TenantService;
import az.restopia.business.util.TenantCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    @Override
    public TenantResponse createTenant(TenantRequest tenantRequest) {
        if (tenantRepository.existsByTenantCode(tenantRequest.getTenantCode())) {
            throw new IllegalArgumentException("Tenant with this code already exists");
        }

        Tenant tenant = tenantMapper.toEntity(tenantRequest);
        tenant.setTenantCode(TenantCodeUtil.generateCode(tenantRequest.getName()));


        Tenant savedTenant = tenantRepository.save(tenant);
        log.debug("New tenant with code: {} is created", savedTenant.getTenantCode());

        return tenantMapper.toResponse(savedTenant);
    }

    @Override
    public TenantResponse updateTenant(String tenantCode, TenantRequest tenantRequest) {
        Tenant tenant = tenantRepository.findByTenantCode(tenantCode)
                .orElseThrow(() -> new IllegalArgumentException("Tenant (" + tenantCode + ") not found"));

        tenant.setName(tenantRequest.getName());
        tenant.setDescription(tenantRequest.getDescription());
        tenant.setTenantSize(tenantRequest.getTenantSize());
        tenant.setLogoUrl(tenantRequest.getLogoUrl());
        tenant.setContactEmail(tenantRequest.getContactEmail());
        tenant.setContactPhone(tenantRequest.getContactPhone());
        tenant.setAddress(tenantRequest.getAddress());
        tenant.setSubscriptionPlan(tenantRequest.getSubscriptionPlan());
        tenant.setSubscriptionStatus(tenantRequest.getSubscriptionStatus());
        tenant.setContractStart(tenantRequest.getContractStart());
        tenant.setContractEnd(tenantRequest.getContractEnd());
        tenant.setFeatureFlags(tenantRequest.getFeatureFlags());

        Tenant savedTenant = tenantRepository.save(tenant);
        log.debug("Tenant: {} is updated", savedTenant.getTenantCode());

        return tenantMapper.toResponse(savedTenant);
    }

    @Override
    public void deleteTenant(String tenantCode) {
        if (!tenantRepository.existsByTenantCode(tenantCode)) {
            throw new IllegalArgumentException("Tenant not found");
        }

        // TODO: do not delete tenant from db, only change its status
    }

    @Override
    public List<TenantResponse> getAllTenants() {
        return tenantRepository.findAll().stream()
                .map(tenantMapper::toResponse)
                .toList();
    }

    @Override
    public TenantResponse getTenantByCode(String tenantCode) {
        return tenantRepository.findByTenantCode(tenantCode)
                .map(tenantMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Tenant (" + tenantCode + ") not found"));
    }

    @Override
    public Tenant getTenantByCodeOrElseThrow(String tenantCode) {
        return tenantRepository.findByTenantCode(tenantCode)
                .orElseThrow(() -> new IllegalArgumentException("Tenant (" + tenantCode + ") not found"));
    }
}
