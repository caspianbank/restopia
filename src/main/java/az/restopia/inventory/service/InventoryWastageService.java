package az.restopia.inventory.service;

import az.restopia.inventory.domain.request.InventoryWastageRequest;
import az.restopia.inventory.domain.response.InventoryWastageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryWastageService {
    Page<InventoryWastageResponse> getAllWastage(String tenantCode, Pageable pageable);

    InventoryWastageResponse getWastageById(String tenantCode, Long id);

    InventoryWastageResponse createWastage(String tenantCode, InventoryWastageRequest request);

    InventoryWastageResponse updateWastage(String tenantCode, Long id, InventoryWastageRequest request);

    void deleteWastage(String tenantCode, Long id);

    Page<InventoryWastageResponse> searchWastage(String tenantCode, String reference, String reason,
                                                 String status, String location, Long inventoryItemId, Pageable pageable);

    InventoryWastageResponse approveWastage(String tenantCode, Long id, String approvedBy, Long approvedById);
}