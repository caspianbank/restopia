package az.restopia.inventory.controller;

import az.restopia.inventory.domain.request.InventoryRequest;
import az.restopia.inventory.domain.response.InventoryResponse;
import az.restopia.inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventories")
@Validated
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventories(
            @RequestParam Long businessId,
            @RequestParam(required = false) Long storeId) {
        log.info("Getting inventories for businessId: {}, storeId: {}", businessId, storeId);
        List<InventoryResponse> inventories = inventoryService.getAllInventories(businessId, storeId);
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable Long id) {
        log.info("Getting inventory by id: {}", id);
        InventoryResponse inventory = inventoryService.getInventoryById(id);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(@Valid @RequestBody InventoryRequest request) {
        log.info("Creating new inventory with name: {}", request.getName());
        InventoryResponse inventory = inventoryService.createInventory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(
            @PathVariable Long id,
            @Valid @RequestBody InventoryRequest request) {
        log.info("Updating inventory with id: {}", id);
        InventoryResponse inventory = inventoryService.updateInventory(id, request);
        return ResponseEntity.ok(inventory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        log.info("Deleting inventory with id: {}", id);
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}