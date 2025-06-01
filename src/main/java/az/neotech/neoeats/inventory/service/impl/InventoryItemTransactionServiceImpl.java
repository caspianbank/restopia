package az.neotech.neoeats.inventory.service.impl;

import az.neotech.neoeats.business.domain.entity.TenantBusinessStore;
import az.neotech.neoeats.business.repository.TenantBusinessStoreRepository;
import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import az.neotech.neoeats.inventory.domain.entity.InventoryItem;
import az.neotech.neoeats.inventory.domain.entity.InventoryItemTransaction;
import az.neotech.neoeats.inventory.domain.mapper.InventoryItemTransactionMapper;
import az.neotech.neoeats.inventory.domain.request.InventoryItemTransactionRequest;
import az.neotech.neoeats.inventory.domain.response.InventoryItemTransactionResponse;
import az.neotech.neoeats.inventory.repository.InventoryItemRepository;
import az.neotech.neoeats.inventory.repository.InventoryItemTransactionRepository;
import az.neotech.neoeats.inventory.service.InventoryItemTransactionService;
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
                .orElseThrow(() -> new RecordNotFoundException("InventoryItem not found with id: " + request.getInventoryItemId()));

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
