package az.restopia.business.service;

import az.restopia.business.domain.entity.Tenant;
import az.restopia.business.domain.request.TenantRequest;
import az.restopia.business.domain.response.TenantResponse;

import java.util.List;

public interface TenantService {

    TenantResponse createTenant(TenantRequest tenantRequest);

    TenantResponse updateTenant(String tenantCode, TenantRequest tenantRequest);

    void deleteTenant(String tenantCode);

    List<TenantResponse> getAllTenants();

    TenantResponse getTenantByCode(String tenantCode);

    Tenant getTenantByCodeOrElseThrow(String tenantCode);
}
