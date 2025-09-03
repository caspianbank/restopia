package az.restopia.inventory.service;

import az.restopia.inventory.domain.request.InventoryDispatchRequest;
import az.restopia.inventory.domain.response.InventoryDispatchResponse;

import java.util.List;

public interface InventoryDispatchService {

    List<InventoryDispatchResponse> getAllInventoryDispatches();

    InventoryDispatchResponse getInventoryDispatchById(Long id);

    InventoryDispatchResponse createInventoryDispatch(InventoryDispatchRequest request);

    InventoryDispatchResponse updateInventoryDispatch(Long id, InventoryDispatchRequest request);

    void deleteInventoryDispatch(Long id);
}
