package az.restopia.inventory.service;

import az.restopia.inventory.domain.request.InventoryRequest;
import az.restopia.inventory.domain.response.InventoryResponse;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> getAllInventories(Long businessId, Long storeId);

    InventoryResponse getInventoryById(Long id);

    InventoryResponse createInventory(InventoryRequest request);

    InventoryResponse updateInventory(Long id, InventoryRequest request);

    void deleteInventory(Long id);
}