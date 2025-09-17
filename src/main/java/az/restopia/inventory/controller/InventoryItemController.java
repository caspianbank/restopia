package az.restopia.inventory.controller;

import az.restopia.inventory.domain.request.InventoryItemRequest;
import az.restopia.inventory.domain.response.InventoryItemResponse;
import az.restopia.inventory.service.InventoryItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory-items")
public class InventoryItemController {
    private final InventoryItemService inventoryItemService;

    @GetMapping
    public List<InventoryItemResponse> getAllInventoryItems() {
        return inventoryItemService.getAllInventoryItems();
    }

    @PostMapping
    public InventoryItemResponse createInventoryItem(@RequestBody @Valid InventoryItemRequest request) {
        return inventoryItemService.createInventoryItem(request);
    }

    @PutMapping("/{id}")
    public InventoryItemResponse updateInventoryItem(
            @PathVariable Long id,
            @RequestBody @Valid InventoryItemRequest request) {
        return inventoryItemService.updateInventoryItem(id, request);
    }

    @GetMapping("/{id}")
    public InventoryItemResponse getInventoryItemById(@PathVariable Long id) {
        return inventoryItemService.getInventoryItemById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteInventoryItem(@PathVariable Long id) {
        inventoryItemService.deleteInventoryItem(id);
    }
}
