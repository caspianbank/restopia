package az.restopia.inventory.controller;

import az.restopia.inventory.domain.request.InventoryItemCategoryRequest;
import az.restopia.inventory.domain.response.InventoryItemCategoryResponse;
import az.restopia.inventory.service.InventoryItemCategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory-item-categories")
public class InventoryItemCategoryController {

    private final InventoryItemCategoryService service;

    @PostMapping
    public ResponseEntity<InventoryItemCategoryResponse> create(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @Valid @RequestBody InventoryItemCategoryRequest request) {

        log.info("Creating inventory item category for tenant: {}", tenantCode);
        InventoryItemCategoryResponse response = service.create(tenantCode, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItemCategoryResponse> findById(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long id) {

        log.info("Finding inventory item category by id: {} for tenant: {}", id, tenantCode);
        InventoryItemCategoryResponse response = service.findById(tenantCode, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<InventoryItemCategoryResponse>> findAll(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PageableDefault(size = 20, sort = {"sortOrder", "name"}, direction = Sort.Direction.ASC) Pageable pageable) {

        log.info("Finding all inventory item categories for tenant: {}", tenantCode);
        Page<InventoryItemCategoryResponse> response = service.findAll(tenantCode, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/root")
    public ResponseEntity<List<InventoryItemCategoryResponse>> findRootCategories(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode) {

        log.info("Finding root categories for tenant: {}", tenantCode);
        List<InventoryItemCategoryResponse> response = service.findRootCategories(tenantCode);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-parent")
    public ResponseEntity<List<InventoryItemCategoryResponse>> findByParent(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @RequestParam(required = false) Long parentId) {

        log.info("Finding categories by parent: {} for tenant: {}", parentId, tenantCode);
        List<InventoryItemCategoryResponse> response = service.findByParent(tenantCode, parentId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryItemCategoryResponse> update(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long id,
            @Valid @RequestBody InventoryItemCategoryRequest request) {

        log.info("Updating inventory item category with id: {} for tenant: {}", id, tenantCode);
        InventoryItemCategoryResponse response = service.update(tenantCode, id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long id) {

        log.info("Deleting inventory item category with id: {} for tenant: {}", id, tenantCode);
        service.delete(tenantCode, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByName(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @RequestParam String name,
            @RequestParam(required = false) Long parentId) {

        log.info("Checking if category exists with name: {} and parent: {} for tenant: {}", name, parentId, tenantCode);
        boolean exists = service.existsByName(tenantCode, name, parentId);
        return ResponseEntity.ok(exists);
    }
}