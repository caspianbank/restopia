package az.restopia.inventory.service;

import az.restopia.inventory.domain.request.InventoryItemRequest;
import az.restopia.inventory.domain.response.InventoryItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryItemService {
    Page<InventoryItemResponse> getInventoryItems(String tenantCode, Long inventoryId, Pageable pageable);

    InventoryItemResponse createInventoryItem(String tenantCode, Long inventoryId, InventoryItemRequest request);

    InventoryItemResponse updateInventoryItem(String tenantCode, Long inventoryId, Long itemId, InventoryItemRequest request);

    void reorderInventoryItem(String tenantCode, Long inventoryId, Long itemId);

    void deleteInventoryItem(String tenantCode, Long inventoryId, Long itemId);

    Page<InventoryItemResponse> searchInventoryItems(String tenantCode, Long inventoryId, String query,
                                                     String sku, String barcode, Boolean perishable, Boolean requiresRefrigeration, Pageable pageable);
}