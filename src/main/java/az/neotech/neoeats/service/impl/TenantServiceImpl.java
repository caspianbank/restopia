package az.neotech.neoeats.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import az.neotech.neoeats.domain.entity.Tenant;
import az.neotech.neoeats.domain.mapper.TenantMapper;
import az.neotech.neoeats.domain.request.TenantRequest;
import az.neotech.neoeats.domain.response.TenantResponse;
import az.neotech.neoeats.repository.TenantRepository;
import az.neotech.neoeats.service.TenantService;
import az.neotech.neoeats.util.TenantCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
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
                .map(tenant -> tenantMapper.toResponse(tenant))
                .toList();
    }

    @Override
    public TenantResponse getTenantByCode(String tenantCode) {
        return tenantRepository.findByTenantCode(tenantCode)
                .map(tenant -> tenantMapper.toResponse(tenant))
                .orElseThrow(() -> new IllegalArgumentException("Tenant (" + tenantCode + ") not found"));
    }
}
