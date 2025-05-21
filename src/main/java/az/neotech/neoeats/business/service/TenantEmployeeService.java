package az.neotech.neoeats.business.service;

import az.neotech.neoeats.business.domain.request.TenantEmployeeRequest;
import az.neotech.neoeats.business.domain.response.TenantEmployeeResponse;

import java.util.List;

public interface TenantEmployeeService {
    TenantEmployeeResponse createEmployee(String tenantCode, TenantEmployeeRequest request);

    TenantEmployeeResponse getTenantEmployeeById(Long id);

    List<TenantEmployeeResponse> getAllTenantEmployees(String tenantCode, Long businessId, Long storeId);

    TenantEmployeeResponse updateTenantEmployee(Long id, TenantEmployeeRequest request);

    void deleteTenantEmployee(Long id);
}
