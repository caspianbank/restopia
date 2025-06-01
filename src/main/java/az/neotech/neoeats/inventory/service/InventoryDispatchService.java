package az.neotech.neoeats.inventory.service;

import az.neotech.neoeats.inventory.domain.request.InventoryDispatchRequest;
import az.neotech.neoeats.inventory.domain.response.InventoryDispatchResponse;

import java.util.List;

public interface InventoryDispatchService {

    List<InventoryDispatchResponse> getAllInventoryDispatches();

    InventoryDispatchResponse getInventoryDispatchById(Long id);

    InventoryDispatchResponse createInventoryDispatch(InventoryDispatchRequest request);

    InventoryDispatchResponse updateInventoryDispatch(Long id, InventoryDispatchRequest request);

    void deleteInventoryDispatch(Long id);
}
