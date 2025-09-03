package az.restopia.inventory.service.impl;

import az.restopia.business.domain.entity.TenantBusinessStore;
import az.restopia.business.repository.TenantBusinessStoreRepository;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.inventory.domain.entity.InventoryItem;
import az.restopia.inventory.domain.entity.InventoryItemTransaction;
import az.restopia.inventory.domain.mapper.InventoryItemTransactionMapper;
import az.restopia.inventory.domain.request.InventoryItemTransactionRequest;
import az.restopia.inventory.domain.response.InventoryItemTransactionResponse;
import az.restopia.inventory.repository.InventoryItemRepository;
import az.restopia.inventory.repository.InventoryItemTransactionRepository;
import az.restopia.inventory.service.InventoryItemTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

// todo: replace tenantBusinessStoreRepository and all must be accessed via service classes
@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryItemTransactionServiceImpl implements InventoryItemTransactionService {

    private final InventoryItemTransactionRepository inventoryItemTransactionRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final TenantBusinessStoreRepository tenantBusinessStoreRepository;
    private final InventoryItemTransactionMapper inventoryItemTransactionMapper;

    @Override
    public List<InventoryItemTransactionResponse> getAllInventoryItemTransactions() {
        return inventoryItemTransactionRepository.findAll()
                .stream()
                .map(inventoryItemTransactionMapper::toResponse)
                .toList();
    }

    @Override
    public InventoryItemTransactionResponse getInventoryItemTransactionById(Long id) {
        InventoryItemTransaction entity = inventoryItemTransactionRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("InventoryItemTransaction not found with id: " + id));
        return inventoryItemTransactionMapper.toResponse(entity);
    }

    @Override
    public InventoryItemTransactionResponse createInventoryItemTransaction(InventoryItemTransactionRequest request) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(request.getInventoryItemId())
                .orElseThrow(() -> new RecordNotFoundException("Inventory Item not found with id: "
                        + request.getInventoryItemId()));

        TenantBusinessStore store = null;
        if (request.getStoreId() != null) {
            store = tenantBusinessStoreRepository.findById(request.getStoreId())
                    .orElseThrow(() -> new RecordNotFoundException("Store not found with id: " + request.getStoreId()));
        }

        InventoryItemTransaction entity = inventoryItemTransactionMapper.toEntity(request);
        entity.setInventoryItem(inventoryItem);
        entity.setStore(store);

        InventoryItemTransaction saved = inventoryItemTransactionRepository.save(entity);
        log.info("InventoryItemTransaction created with id: {}", saved.getId());
        return inventoryItemTransactionMapper.toResponse(saved);
    }
}
