package az.restopia.business.service;

import az.restopia.business.domain.request.TenantEmployeeRequest;
import az.restopia.business.domain.request.TimeOffRequest;
import az.restopia.business.domain.response.EmployeeCountResponse;
import az.restopia.business.domain.response.TenantEmployeeResponse;
import az.restopia.business.domain.response.TimeOffResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TenantEmployeeService {
    Page<TenantEmployeeResponse> getAllEmployees(String tenantCode, Pageable pageable);

    TenantEmployeeResponse getEmployeeById(String tenantCode, Long id);

    Page<TenantEmployeeResponse> searchEmployees(String tenantCode, String searchTerm, Pageable pageable);

    Page<TenantEmployeeResponse> getEmployeesByRole(String tenantCode, String role, Pageable pageable);

    TenantEmployeeResponse createEmployee(String tenantCode, TenantEmployeeRequest request);

    TenantEmployeeResponse updateEmployee(String tenantCode, Long id, TenantEmployeeRequest request);

    void deactivateEmployee(String tenantCode, Long id);

    EmployeeCountResponse getEmployeeCount(String tenantCode);

    TimeOffResponse createTimeOffRequest(String tenantCode, TimeOffRequest request);

    void approveTimeOffRequest(String tenantCode, Long id);

    void rejectTimeOffRequest(String tenantCode, Long id);

    TimeOffResponse updateTimeOffRequest(String tenantCode, Long id, TimeOffRequest request);

    void deleteTimeOffRequest(String tenantCode, Long id);

    Long getTimeOffRequestsCount(String tenantCode);
}
