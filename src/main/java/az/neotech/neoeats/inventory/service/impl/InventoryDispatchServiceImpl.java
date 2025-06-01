package az.neotech.neoeats.inventory.service.impl;

import az.neotech.neoeats.business.domain.entity.TenantBusinessStore;
import az.neotech.neoeats.business.repository.TenantBusinessStoreRepository;
import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import az.neotech.neoeats.inventory.domain.entity.InventoryDispatch;
import az.neotech.neoeats.inventory.domain.entity.InventoryDispatchItem;
import az.neotech.neoeats.inventory.domain.entity.InventoryItem;
import az.neotech.neoeats.inventory.domain.mapper.InventoryDispatchMapper;
import az.neotech.neoeats.inventory.domain.request.InventoryDispatchRequest;
import az.neotech.neoeats.inventory.domain.response.InventoryDispatchResponse;
import az.neotech.neoeats.inventory.repository.InventoryDispatchRepository;
import az.neotech.neoeats.inventory.service.InventoryDispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

// todo: find by id methods for removing code duplication
@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryDispatchServiceImpl implements InventoryDispatchService {

    private final InventoryDispatchRepository inventoryDispatchRepository;
    private final InventoryDispatchMapper inventoryDispatchMapper;
    private final TenantBusinessStoreRepository tenantBusinessStoreRepository;

    @Override
    public List<InventoryDispatchResponse> getAllInventoryDispatches() {
        return inventoryDispatchRepository.findAll().stream()
                .map(inventoryDispatchMapper::toResponse)
                .toList();
    }

    @Override
    public InventoryDispatchResponse getInventoryDispatchById(Long id) {
        InventoryDispatch dispatch = inventoryDispatchRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Inventory dispatch not found with id: " + id));
        return inventoryDispatchMapper.toResponse(dispatch);
    }

    @Override
    public InventoryDispatchResponse createInventoryDispatch(InventoryDispatchRequest request) {
        InventoryDispatch dispatch = inventoryDispatchMapper.toEntity(request);

        TenantBusinessStore toStore = tenantBusinessStoreRepository.findById(request.getToStoreId())
                .orElseThrow(() -> new RecordNotFoundException("Store not found with id: " + request.getToStoreId()));
        dispatch.setToStore(toStore);

        dispatch.getItems().forEach(item -> item.setDispatch(dispatch));

        InventoryDispatch saved = inventoryDispatchRepository.save(dispatch);
        return inventoryDispatchMapper.toResponse(saved);
    }

    @Override
    public InventoryDispatchResponse updateInventoryDispatch(Long id, InventoryDispatchRequest request) {
        InventoryDispatch existing = inventoryDispatchRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Inventory dispatch not found with id: " + id));

        inventoryDispatchMapper.updateInventoryDispatchFromRequest(request, existing);

        TenantBusinessStore toStore = tenantBusinessStoreRepository.findById(request.getToStoreId())
                .orElseThrow(() -> new RecordNotFoundException("Store not found with id: " + request.getToStoreId()));
        existing.setToStore(toStore);

        existing.getItems().clear();
        request.getItems().forEach(reqItem -> {
            var newItem = new InventoryDispatchItem();
            newItem.setInventoryItem(InventoryItem.builder().id(reqItem.getInventoryItemId()).build());
            newItem.setQuantity(reqItem.getQuantity());
            newItem.setDispatch(existing);
            existing.getItems().add(newItem);
        });

        InventoryDispatch updated = inventoryDispatchRepository.save(existing);
        return inventoryDispatchMapper.toResponse(updated);
    }

    @Override
    public void deleteInventoryDispatch(Long id) {
        InventoryDispatch existing = inventoryDispatchRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Inventory dispatch not found with id: " + id));
        inventoryDispatchRepository.delete(existing);
        log.info("Inventory dispatch deleted with id: {}", id);
    }
}
