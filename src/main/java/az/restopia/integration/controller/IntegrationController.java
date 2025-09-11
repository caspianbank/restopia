package az.restopia.integration.controller;

import az.restopia.integration.domain.response.IntegrationResponse;
import az.restopia.integration.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/integrations")
public class IntegrationController {

    private final IntegrationService integrationService;

    @GetMapping
    public ResponseEntity<List<IntegrationResponse>> getAllIntegrations(
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("Getting all integrations for tenant: {}", tenantCode);
        return ResponseEntity.ok(integrationService.getAllIntegrations(tenantCode));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<IntegrationResponse> getIntegrationByCode(
            @PathVariable String code,
            @RequestHeader("X-Tenant-Code") String tenantCode) {
        log.info("Getting integration by code: {} for tenant: {}", code, tenantCode);
        return ResponseEntity.ok(integrationService.getIntegrationByCode(code, tenantCode));
    }

}
