package az.neotech.neoeats.business.service;

import az.neotech.neoeats.business.domain.request.TenantRequest;
import az.neotech.neoeats.business.domain.response.TenantResponse;

import java.util.List;

public interface TenantService {

    TenantResponse createTenant(TenantRequest tenantRequest);

    TenantResponse updateTenant(String tenantCode, TenantRequest tenantRequest);

    void deleteTenant(String tenantCode);

    List<TenantResponse> getAllTenants();

    TenantResponse getTenantByCode(String tenantCode);
}
