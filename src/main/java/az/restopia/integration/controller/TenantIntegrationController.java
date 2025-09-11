package az.restopia.integration.controller;

import az.restopia.integration.domain.enums.IntegrationStatus;
import az.restopia.integration.domain.request.TenantIntegrationRequest;
import az.restopia.integration.domain.response.TenantIntegrationResponse;
import az.restopia.integration.service.TenantIntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tenant-integrations")
public class TenantIntegrationController {

    private final TenantIntegrationService tenantIntegrationService;

    @PostMapping
    public ResponseEntity<TenantIntegrationResponse> addIntegrationForTenant(
            @Valid @RequestBody TenantIntegrationRequest request,
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("Adding integration {} for tenant: {}", request.getIntegrationCode(), tenantCode);
        
        TenantIntegrationResponse response = tenantIntegrationService.addIntegrationForTenant(tenantCode, request);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{code}/status")
    public ResponseEntity<TenantIntegrationResponse> updateTenantIntegrationStatus(
            @PathVariable("code") String integrationCode,
            @RequestParam("value") IntegrationStatus value,
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("Updating tenant integration status to {} for tenant: {}, id: {}", value, tenantCode, integrationCode);
        
        TenantIntegrationResponse response = tenantIntegrationService.updateTenantIntegrationStatus(tenantCode, integrationCode, value);
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteTenantIntegration(
            @PathVariable("code") String integrationCode,
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("Deleting tenant integration for tenant: {}, id: {}", tenantCode, integrationCode);
        
        tenantIntegrationService.deleteTenantIntegration(tenantCode, integrationCode);
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{code}")
    public ResponseEntity<TenantIntegrationResponse> getTenantIntegrationById(
            @PathVariable("code") String integrationCode,
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("Getting tenant integration for tenant: {}, id: {}", tenantCode, integrationCode);
        
        TenantIntegrationResponse response = tenantIntegrationService.getTenantIntegrationById(tenantCode, integrationCode);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TenantIntegrationResponse>> getTenantIntegrations(
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("Getting all tenant integrations for tenant: {}", tenantCode);
        
        List<TenantIntegrationResponse> response = tenantIntegrationService.getTenantIntegrations(tenantCode);
        
        return ResponseEntity.ok(response);
    }
}