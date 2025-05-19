package az.neotech.neoeats.service;

import java.util.List;

import az.neotech.neoeats.domain.request.TenantRequest;
import az.neotech.neoeats.domain.response.TenantResponse;

public interface TenantService {

    TenantResponse createTenant(TenantRequest tenantRequest);

    TenantResponse updateTenant(String tenantCode, TenantRequest tenantRequest);

    void deleteTenant(String tenantCode);

    List<TenantResponse> getAllTenants();

    TenantResponse getTenantByCode(String tenantCode);
}
