package az.restopia.inventory.controller;

import az.restopia.inventory.domain.request.InventoryItemRequest;
import az.restopia.inventory.domain.response.InventoryItemResponse;
import az.restopia.inventory.service.InventoryItemService;
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
@RequestMapping("/api/v1/inventories")
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;

    @GetMapping("/{inventoryId}/items")
    public ResponseEntity<Page<InventoryItemResponse>> getInventoryItems(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long inventoryId,
            Pageable pageable) {
        log.debug("Getting inventory items for inventory: {} and tenant: {}", inventoryId, tenantCode);
        Page<InventoryItemResponse> items = inventoryItemService.getInventoryItems(tenantCode, inventoryId, pageable);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/{inventoryId}/items")
    public ResponseEntity<InventoryItemResponse> createInventoryItem(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long inventoryId,
            @Valid @RequestBody InventoryItemRequest request) {
        log.debug("Creating inventory item for inventory: {} and tenant: {}", inventoryId, tenantCode);
        InventoryItemResponse response = inventoryItemService.createInventoryItem(tenantCode, inventoryId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{inventoryId}/items/{itemId}")
    public ResponseEntity<InventoryItemResponse> updateInventoryItem(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long inventoryId,
            @PathVariable Long itemId,
            @Valid @RequestBody InventoryItemRequest request) {
        log.debug("Updating inventory item: {} for inventory: {} and tenant: {}", itemId, inventoryId, tenantCode);
        InventoryItemResponse response = inventoryItemService.updateInventoryItem(tenantCode, inventoryId, itemId, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{inventoryId}/items/{itemId}/reorder")
    public ResponseEntity<Void> reorderInventoryItem(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long inventoryId,
            @PathVariable Long itemId) {
        log.debug("Reordering inventory item: {} for inventory: {} and tenant: {}", itemId, inventoryId, tenantCode);
        inventoryItemService.reorderInventoryItem(tenantCode, inventoryId, itemId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{inventoryId}/items/{itemId}")
    public ResponseEntity<Void> deleteInventoryItem(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long inventoryId,
            @PathVariable Long itemId) {
        log.debug("Deleting inventory item: {} for inventory: {} and tenant: {}", itemId, inventoryId, tenantCode);
        inventoryItemService.deleteInventoryItem(tenantCode, inventoryId, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{inventoryId}/items/search")
    public ResponseEntity<Page<InventoryItemResponse>> searchInventoryItems(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @PathVariable Long inventoryId,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String sku,
            @RequestParam(required = false) String barcode,
            @RequestParam(required = false) Boolean perishable,
            @RequestParam(required = false) Boolean requiresRefrigeration,
            Pageable pageable) {
        log.debug("Searching inventory items for inventory: {} and tenant: {} with query: {}", inventoryId, tenantCode, query);
        Page<InventoryItemResponse> items = inventoryItemService.searchInventoryItems(
                tenantCode, inventoryId, query, sku, barcode, perishable, requiresRefrigeration, pageable);
        return ResponseEntity.ok(items);
    }
}