package az.restopia.inventory.service;

import az.restopia.inventory.domain.request.InventoryItemRequest;
import az.restopia.inventory.domain.response.InventoryItemResponse;

import java.util.List;

public interface InventoryItemService {

    InventoryItemResponse createInventoryItem(InventoryItemRequest request);

    InventoryItemResponse updateInventoryItem(Long id, InventoryItemRequest request);

    InventoryItemResponse getInventoryItemById(Long id);

    List<InventoryItemResponse> getAllInventoryItems();

    void deleteInventoryItem(Long id);
}
