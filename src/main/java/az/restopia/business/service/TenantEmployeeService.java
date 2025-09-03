package az.restopia.business.service;

import az.restopia.business.domain.request.TenantEmployeeRequest;
import az.restopia.business.domain.response.TenantEmployeeResponse;

import java.util.List;

public interface TenantEmployeeService {
    TenantEmployeeResponse createEmployee(String tenantCode, TenantEmployeeRequest request);

    TenantEmployeeResponse getTenantEmployeeById(Long id);

    List<TenantEmployeeResponse> getAllTenantEmployees(String tenantCode, Long businessId, Long storeId);

    TenantEmployeeResponse updateTenantEmployee(Long id, TenantEmployeeRequest request);

    void deleteTenantEmployee(Long id);
}
