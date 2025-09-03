package az.restopia.business.controller;

import az.restopia.business.domain.request.TenantEmployeeRequest;
import az.restopia.business.domain.response.TenantEmployeeResponse;
import az.restopia.business.service.TenantEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/tenants/{tenantCode}/employees")
@RequiredArgsConstructor
public class TenantEmployeeController {
    private final TenantEmployeeService tenantEmployeeService;

    @PostMapping
    public TenantEmployeeResponse createEmployee(
            @PathVariable(name = "tenantCode") String tenantCode,
            @RequestBody @Valid TenantEmployeeRequest request) {
        return tenantEmployeeService.createEmployee(tenantCode, request);
    }

    // todo: businessId and storeId can be optional for filter. In future, it must support pagination.
    @GetMapping
    public List<TenantEmployeeResponse> getAllEmployees(
            @PathVariable String tenantCode,
            @RequestParam("businessId") Long businessId,
            @RequestParam("storeId") Long storeId
    ) {
        return tenantEmployeeService.getAllTenantEmployees(tenantCode, businessId, storeId);
    }

    @GetMapping("/{id}")
    public TenantEmployeeResponse getEmployeeById(@PathVariable Long id) {
        return tenantEmployeeService.getTenantEmployeeById(id);
    }

    @PutMapping("/{id}")
    public TenantEmployeeResponse updateEmployee(
            @PathVariable Long id,
            @RequestBody @Valid TenantEmployeeRequest request) {
        return tenantEmployeeService.updateTenantEmployee(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        tenantEmployeeService.deleteTenantEmployee(id);
    }
}
