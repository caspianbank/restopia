package az.restopia.inventory.controller;

import az.restopia.inventory.domain.request.InventoryWastageRequest;
import az.restopia.inventory.domain.response.InventoryWastageResponse;
import az.restopia.inventory.service.InventoryWastageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory-waste")
public class InventoryWastageController {

    private final InventoryWastageService inventoryWastageService;

    @GetMapping
    public ResponseEntity<Page<InventoryWastageResponse>> getAllWastages(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            Pageable pageable) {
        log.debug("Getting all wastage for tenant: {}", tenantCode);
        return ResponseEntity.ok(inventoryWastageService.getAllWastage(tenantCode, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryWastageResponse> getWastageById(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long id) {
        log.debug("Getting wastage by id: {} for tenant: {}", id, tenantCode);
        return ResponseEntity.ok(inventoryWastageService.getWastageById(tenantCode, id));
    }

    @PostMapping
    public ResponseEntity<InventoryWastageResponse> createWastage(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @Valid @RequestBody InventoryWastageRequest request) {
        log.debug("Creating wastage for tenant: {}", tenantCode);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inventoryWastageService.createWastage(tenantCode, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryWastageResponse> updateWastage(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long id,
            @Valid @RequestBody InventoryWastageRequest request) {
        log.debug("Updating wastage: {} for tenant: {}", id, tenantCode);
        return ResponseEntity.ok(inventoryWastageService.updateWastage(tenantCode, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWastage(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long id) {
        log.debug("Deleting wastage: {} for tenant: {}", id, tenantCode);
        inventoryWastageService.deleteWastage(tenantCode, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<InventoryWastageResponse>> searchWastages(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @RequestParam(required = false) String reference,
            @RequestParam(required = false) String reason,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Long inventoryItemId,
            Pageable pageable) {
        log.debug("Searching wastage for tenant: {} with filters", tenantCode);
        return ResponseEntity.ok(inventoryWastageService.searchWastage(
                tenantCode, reference, reason, status, location, inventoryItemId, pageable));
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<InventoryWastageResponse> approveWastage(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long id,
            @RequestParam String approvedBy,
            @RequestParam Long approvedById) {
        log.debug("Approving wastage: {} for tenant: {}", id, tenantCode);
        InventoryWastageResponse response = inventoryWastageService.approveWastage(tenantCode, id, approvedBy, approvedById);
        return ResponseEntity.ok(response);
    }
}