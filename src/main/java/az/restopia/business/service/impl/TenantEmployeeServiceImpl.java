package az.restopia.business.service.impl;

import az.restopia.business.domain.entity.TenantBusiness;
import az.restopia.business.domain.entity.TenantBusinessStore;
import az.restopia.business.domain.entity.TenantEmployee;
import az.restopia.business.domain.mapper.TenantEmployeeMapper;
import az.restopia.business.domain.request.TenantEmployeeRequest;
import az.restopia.business.domain.response.TenantEmployeeResponse;
import az.restopia.business.repository.TenantBusinessRepository;
import az.restopia.business.repository.TenantBusinessStoreRepository;
import az.restopia.business.repository.TenantEmployeeRepository;
import az.restopia.business.service.TenantEmployeeService;
import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantEmployeeServiceImpl implements TenantEmployeeService {

    private final TenantEmployeeRepository tenantEmployeeRepository;
    private final TenantEmployeeMapper tenantEmployeeMapper;
    private final TenantBusinessRepository businessRepository;
    private final TenantBusinessStoreRepository storeRepository;

    @Override
    public TenantEmployeeResponse createEmployee(String tenantCode, TenantEmployeeRequest request) {
        TenantBusiness business = businessRepository.findById(request.getBusinessId())
                .orElseThrow(() -> new RecordNotFoundException("Business not found"));

        TenantBusinessStore store = null;
        if (request.getStoreId() != null) {
            store = storeRepository.findById(request.getStoreId())
                    .orElseThrow(() -> new RecordNotFoundException("Store not found"));
        }

        TenantEmployee employee = tenantEmployeeMapper.toEntity(request);
        employee.setBusiness(business);
        employee.setStore(store);
        employee.setDeleteStatus(DeleteStatus.ACTIVE);

        TenantEmployee savedEmployee = tenantEmployeeRepository.save(employee);
        log.info("{} is created", savedEmployee.getFullName());
        return tenantEmployeeMapper.toResponse(savedEmployee);
    }

    @Override
    public TenantEmployeeResponse getTenantEmployeeById(Long id) {
        return tenantEmployeeRepository.findById(id)
                .map(tenantEmployeeMapper::toResponse)
                .orElseThrow(() -> new RecordNotFoundException("Employee not found"));
    }

    @Override
    public List<TenantEmployeeResponse> getAllTenantEmployees(String tenantCode, Long businessId, Long storeId) {
        return tenantEmployeeRepository.findAll().stream()
                .map(tenantEmployeeMapper::toResponse)
                .toList();
    }

    @Override
    public TenantEmployeeResponse updateTenantEmployee(Long id, TenantEmployeeRequest request) {
        TenantEmployee employee = tenantEmployeeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Employee not found"));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(String.join("|", request.getPhoneNumbers()));
        employee.setRole(request.getRole());

        if (request.getStoreId() != null) {
            TenantBusinessStore store = storeRepository.findById(request.getStoreId())
                    .orElseThrow(() -> new RecordNotFoundException("Store not found"));
            employee.setStore(store);
        }

        TenantEmployee updatedEmployee = tenantEmployeeRepository.save(employee);
        log.info("{} is updated", updatedEmployee.getFullName());

        return tenantEmployeeMapper.toResponse(updatedEmployee);
    }

    @Override
    public void deleteTenantEmployee(Long id) {
        TenantEmployee employee = tenantEmployeeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Employee not found"));
        employee.setDeleteStatus(DeleteStatus.DELETED);
        tenantEmployeeRepository.save(employee);
        log.info("{} is soft deleted", employee.getFullName());
    }
}
