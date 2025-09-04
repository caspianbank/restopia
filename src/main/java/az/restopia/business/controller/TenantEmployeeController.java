package az.restopia.business.controller;

import az.restopia.business.domain.request.TenantEmployeeRequest;
import az.restopia.business.domain.response.TenantEmployeeResponse;
import az.restopia.business.service.TenantEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import az.restopia.business.domain.request.TimeOffRequest;
import az.restopia.business.domain.response.TimeOffResponse;
import az.restopia.business.domain.response.EmployeeCountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class TenantEmployeeController {

    private final TenantEmployeeService tenantEmployeeService;

    @GetMapping
    public ResponseEntity<Page<TenantEmployeeResponse>> getAllEmployees(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            Pageable pageable) {
        Page<TenantEmployeeResponse> employees = tenantEmployeeService.getAllEmployees(tenantCode, pageable);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantEmployeeResponse> getEmployeeById(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id) {
        TenantEmployeeResponse employee = tenantEmployeeService.getEmployeeById(tenantCode, id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<TenantEmployeeResponse>> searchEmployees(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @RequestParam String q,
            Pageable pageable) {
        Page<TenantEmployeeResponse> employees = tenantEmployeeService.searchEmployees(tenantCode, q, pageable);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/by-role")
    public ResponseEntity<Page<TenantEmployeeResponse>> getEmployeesByRole(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @RequestParam String role,
            Pageable pageable) {
        Page<TenantEmployeeResponse> employees = tenantEmployeeService.getEmployeesByRole(tenantCode, role, pageable);
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<TenantEmployeeResponse> createEmployee(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @Valid @RequestBody TenantEmployeeRequest request) {
        TenantEmployeeResponse response = tenantEmployeeService.createEmployee(tenantCode, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantEmployeeResponse> updateEmployee(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id,
            @Valid @RequestBody TenantEmployeeRequest request) {
        TenantEmployeeResponse response = tenantEmployeeService.updateEmployee(tenantCode, id, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateEmployee(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id) {
        tenantEmployeeService.deactivateEmployee(tenantCode, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<EmployeeCountResponse> getEmployeeCount(
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        EmployeeCountResponse count = tenantEmployeeService.getEmployeeCount(tenantCode);
        return ResponseEntity.ok(count);
    }

    @PostMapping("/time-off-requests")
    public ResponseEntity<TimeOffResponse> createTimeOffRequest(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @Valid @RequestBody TimeOffRequest request) {
        TimeOffResponse response = tenantEmployeeService.createTimeOffRequest(tenantCode, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/time-off-requests/{id}/approve")
    public ResponseEntity<Void> approveTimeOffRequest(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id) {
        tenantEmployeeService.approveTimeOffRequest(tenantCode, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/time-off-requests/{id}/reject")
    public ResponseEntity<Void> rejectTimeOffRequest(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id) {
        tenantEmployeeService.rejectTimeOffRequest(tenantCode, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/time-off-requests/{id}")
    public ResponseEntity<TimeOffResponse> updateTimeOffRequest(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id,
            @Valid @RequestBody TimeOffRequest request) {
        TimeOffResponse response = tenantEmployeeService.updateTimeOffRequest(tenantCode, id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/time-off-requests/{id}")
    public ResponseEntity<Void> deleteTimeOffRequest(
            @RequestHeader("X-Tenant-Code") String tenantCode,
            @PathVariable Long id) {
        tenantEmployeeService.deleteTimeOffRequest(tenantCode, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/time-off-requests/count")
    public ResponseEntity<Long> getTimeOffRequestsCount(
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        Long count = tenantEmployeeService.getTimeOffRequestsCount(tenantCode);
        return ResponseEntity.ok(count);
    }
}