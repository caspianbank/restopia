package az.restopia.business.service.impl;

import az.restopia.business.domain.entity.TenantBusiness;
import az.restopia.business.domain.entity.TenantBusinessStore;
import az.restopia.business.domain.entity.TenantEmployee;
import az.restopia.business.domain.entity.TimeOffRequestEntity;
import az.restopia.business.domain.enums.TimeOffStatus;
import az.restopia.business.domain.mapper.TenantEmployeeMapper;
import az.restopia.business.domain.mapper.TimeOffRequestMapper;
import az.restopia.business.domain.request.TenantEmployeeRequest;
import az.restopia.business.domain.request.TimeOffRequest;
import az.restopia.business.domain.response.EmployeeCountResponse;
import az.restopia.business.domain.response.TenantEmployeeResponse;
import az.restopia.business.domain.response.TimeOffResponse;
import az.restopia.business.repository.TenantBusinessRepository;
import az.restopia.business.repository.TenantBusinessStoreRepository;
import az.restopia.business.repository.TenantEmployeeRepository;
import az.restopia.business.repository.TimeOffRequestRepository;
import az.restopia.business.service.TenantEmployeeService;
import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantEmployeeServiceImpl implements TenantEmployeeService {

    private final TenantEmployeeRepository tenantEmployeeRepository;
    private final TenantBusinessRepository tenantBusinessRepository;
    private final TenantBusinessStoreRepository tenantBusinessStoreRepository;
    private final TimeOffRequestRepository timeOffRequestRepository;
    private final TenantEmployeeMapper tenantEmployeeMapper;
    private final TimeOffRequestMapper timeOffRequestMapper;

    @Override
    public Page<TenantEmployeeResponse> getAllEmployees(String tenantCode, Pageable pageable) {
        Page<TenantEmployee> employees = tenantEmployeeRepository
                .findByTenantCodeAndDeleteStatus(tenantCode, DeleteStatus.ACTIVE, pageable);
        return employees.map(tenantEmployeeMapper::toResponse);
    }

    @Override
    public TenantEmployeeResponse getEmployeeById(String tenantCode, Long id) {
        TenantEmployee employee = tenantEmployeeRepository
                .findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Employee not found"));
        return tenantEmployeeMapper.toResponse(employee);
    }

    @Override
    public Page<TenantEmployeeResponse> searchEmployees(String tenantCode, String searchTerm, Pageable pageable) {
        Page<TenantEmployee> employees = tenantEmployeeRepository
                .searchEmployees(tenantCode, DeleteStatus.ACTIVE, searchTerm, pageable);
        return employees.map(tenantEmployeeMapper::toResponse);
    }

    @Override
    public Page<TenantEmployeeResponse> getEmployeesByRole(String tenantCode, String role, Pageable pageable) {
        Page<TenantEmployee> employees = tenantEmployeeRepository
                .findByTenantCodeAndDeleteStatusAndRole(tenantCode, DeleteStatus.ACTIVE, role, pageable);
        return employees.map(tenantEmployeeMapper::toResponse);
    }

    @Override
    @Transactional
    public TenantEmployeeResponse createEmployee(String tenantCode, TenantEmployeeRequest request) {
        TenantBusiness business = tenantBusinessRepository
                .findByIdAndTenantCodeAndDeleteStatus(request.getBusinessId(), tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Business not found"));

        TenantBusinessStore store = null;
        if (request.getStoreId() != null) {
            store = tenantBusinessStoreRepository
                    .findByIdAndBusinessTenantCodeAndDeleteStatus(request.getStoreId(), tenantCode, DeleteStatus.ACTIVE)
                    .orElseThrow(() -> new RecordNotFoundException("Store not found"));
        }

        TenantEmployee employee = tenantEmployeeMapper.toEntity(request);
        employee.setTenantCode(tenantCode);
        employee.setBusiness(business);
        employee.setStore(store);
        employee.setDeleteStatus(DeleteStatus.ACTIVE);

        TenantEmployee savedEmployee = tenantEmployeeRepository.save(employee);
        return tenantEmployeeMapper.toResponse(savedEmployee);
    }

    @Override
    @Transactional
    public TenantEmployeeResponse updateEmployee(String tenantCode, Long id, TenantEmployeeRequest request) {
        TenantEmployee employee = tenantEmployeeRepository
                .findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Employee not found"));

        TenantBusiness business = tenantBusinessRepository
                .findByIdAndTenantCodeAndDeleteStatus(request.getBusinessId(), tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Business not found"));

        TenantBusinessStore store = null;
        if (request.getStoreId() != null) {
            store = tenantBusinessStoreRepository
                    .findByIdAndBusinessTenantCodeAndDeleteStatus(request.getStoreId(), tenantCode, DeleteStatus.ACTIVE)
                    .orElseThrow(() -> new RecordNotFoundException("Store not found"));
        }

        tenantEmployeeMapper.updateEntity(request, employee);
        employee.setBusiness(business);
        employee.setStore(store);

        TenantEmployee savedEmployee = tenantEmployeeRepository.save(employee);
        return tenantEmployeeMapper.toResponse(savedEmployee);
    }

    @Override
    @Transactional
    public void deactivateEmployee(String tenantCode, Long id) {
        TenantEmployee employee = tenantEmployeeRepository
                .findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Employee not found"));

        employee.setActive(false);
        tenantEmployeeRepository.save(employee);
    }

    @Override
    public EmployeeCountResponse getEmployeeCount(String tenantCode) {
        long totalEmployees = tenantEmployeeRepository.countByTenantCodeAndDeleteStatus(tenantCode, DeleteStatus.ACTIVE);
        long activeEmployees = tenantEmployeeRepository.countByTenantCodeAndDeleteStatusAndIsActive(tenantCode, DeleteStatus.ACTIVE, true);
        long inactiveEmployees = totalEmployees - activeEmployees;

        EmployeeCountResponse response = new EmployeeCountResponse();
        response.setTotalEmployees(totalEmployees);
        response.setActiveEmployees(activeEmployees);
        response.setInactiveEmployees(inactiveEmployees);

        return response;
    }

    @Override
    @Transactional
    public TimeOffResponse createTimeOffRequest(String tenantCode, TimeOffRequest request) {
        TenantEmployee employee = tenantEmployeeRepository
                .findByIdAndTenantCodeAndDeleteStatus(request.getEmployeeId(), tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Employee not found"));

        TimeOffRequestEntity timeOffRequest = timeOffRequestMapper.toEntity(request);
        timeOffRequest.setEmployee(employee);
        timeOffRequest.setTenantCode(tenantCode);
        timeOffRequest.setStatus(TimeOffStatus.PENDING);
        timeOffRequest.setDeleteStatus(DeleteStatus.ACTIVE);

        TimeOffRequestEntity savedRequest = timeOffRequestRepository.save(timeOffRequest);
        return timeOffRequestMapper.toResponse(savedRequest);
    }

    @Override
    @Transactional
    public void approveTimeOffRequest(String tenantCode, Long id) {
        TimeOffRequestEntity timeOffRequest = timeOffRequestRepository
                .findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Time off request not found"));

        timeOffRequest.setStatus(TimeOffStatus.APPROVED);
        timeOffRequestRepository.save(timeOffRequest);
    }

    @Override
    @Transactional
    public void rejectTimeOffRequest(String tenantCode, Long id) {
        TimeOffRequestEntity timeOffRequest = timeOffRequestRepository
                .findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Time off request not found"));

        timeOffRequest.setStatus(TimeOffStatus.REJECTED);
        timeOffRequestRepository.save(timeOffRequest);
    }

    @Override
    @Transactional
    public TimeOffResponse updateTimeOffRequest(String tenantCode, Long id, TimeOffRequest request) {
        TimeOffRequestEntity timeOffRequest = timeOffRequestRepository
                .findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Time off request not found"));

        TenantEmployee employee = tenantEmployeeRepository
                .findByIdAndTenantCodeAndDeleteStatus(request.getEmployeeId(), tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Employee not found"));

        timeOffRequestMapper.updateEntity(request, timeOffRequest);
        timeOffRequest.setEmployee(employee);

        TimeOffRequestEntity savedRequest = timeOffRequestRepository.save(timeOffRequest);
        return timeOffRequestMapper.toResponse(savedRequest);
    }

    @Override
    @Transactional
    public void deleteTimeOffRequest(String tenantCode, Long id) {
        TimeOffRequestEntity timeOffRequest = timeOffRequestRepository
                .findByIdAndTenantCodeAndDeleteStatus(id, tenantCode, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Time off request not found"));

        timeOffRequest.setDeleteStatus(DeleteStatus.DELETED);
        timeOffRequestRepository.save(timeOffRequest);
    }

    @Override
    public Long getTimeOffRequestsCount(String tenantCode) {
        return timeOffRequestRepository.countByTenantCodeAndDeleteStatus(tenantCode, DeleteStatus.ACTIVE);
    }
}
