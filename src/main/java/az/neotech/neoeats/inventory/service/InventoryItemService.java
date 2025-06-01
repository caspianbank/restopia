package az.neotech.neoeats.inventory.service;

import az.neotech.neoeats.inventory.domain.request.InventoryItemRequest;
import az.neotech.neoeats.inventory.domain.response.InventoryItemResponse;

import java.util.List;

public interface InventoryItemService {
    InventoryItemResponse createInventoryItem(InventoryItemRequest request);
    InventoryItemResponse updateInventoryItem(Long id, InventoryItemRequest request);
    InventoryItemResponse getInventoryItemById(Long id);
    List<InventoryItemResponse> getAllInventoryItems();
    void deleteInventoryItem(Long id);
}
