package az.restopia.inventory.service.impl;

import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.inventory.domain.entity.Inventory;
import az.restopia.inventory.domain.entity.InventoryItem;
import az.restopia.inventory.domain.mapper.InventoryItemMapper;
import az.restopia.inventory.domain.request.InventoryItemRequest;
import az.restopia.inventory.domain.response.InventoryItemResponse;
import az.restopia.inventory.repository.InventoryItemRepository;
import az.restopia.inventory.repository.InventoryRepository;
import az.restopia.inventory.service.InventoryItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryItemServiceImpl implements InventoryItemService {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryItemMapper inventoryItemMapper;

    @Override
    public Page<InventoryItemResponse> getInventoryItems(String tenantCode, Long inventoryId, Pageable pageable) {
        validateInventoryExists(tenantCode, inventoryId);
        Page<InventoryItem> items = inventoryItemRepository.findByTenantCodeAndInventoryId(tenantCode, inventoryId, pageable);
        return items.map(inventoryItemMapper::toResponse);
    }

    @Override
    @Transactional
    public InventoryItemResponse createInventoryItem(String tenantCode, Long inventoryId, InventoryItemRequest request) {
        Inventory inventory = validateInventoryExists(tenantCode, inventoryId);

        if (inventoryItemRepository.existsByTenantCodeAndSku(tenantCode, request.getSku())) {
            throw new IllegalArgumentException("SKU already exists for this tenant: " + request.getSku());
        }

        InventoryItem item = inventoryItemMapper.toEntity(request);
        item.setTenantCode(tenantCode);
        item.setInventory(inventory);

        InventoryItem savedItem = inventoryItemRepository.save(item);
        log.info("Created inventory item with SKU: {} for tenant: {}", savedItem.getSku(), tenantCode);

        return inventoryItemMapper.toResponse(savedItem);
    }

    @Override
    @Transactional
    public InventoryItemResponse updateInventoryItem(String tenantCode, Long inventoryId, Long itemId, InventoryItemRequest request) {
        validateInventoryExists(tenantCode, inventoryId);

        InventoryItem existingItem = inventoryItemRepository.findByIdAndTenantCodeAndInventoryId(itemId, tenantCode, inventoryId)
                .orElseThrow(() -> new RecordNotFoundException("Inventory item not found with id: " + itemId));

        if (!existingItem.getSku().equals(request.getSku()) &&
                inventoryItemRepository.existsByTenantCodeAndSku(tenantCode, request.getSku())) {
            throw new IllegalArgumentException("SKU already exists for this tenant: " + request.getSku());
        }

        inventoryItemMapper.updateEntity(request, existingItem);
        InventoryItem updatedItem = inventoryItemRepository.save(existingItem);
        log.info("Updated inventory item with id: {} for tenant: {}", itemId, tenantCode);

        return inventoryItemMapper.toResponse(updatedItem);
    }

    @Override
    @Transactional
    public void reorderInventoryItem(String tenantCode, Long inventoryId, Long itemId) {
        validateInventoryExists(tenantCode, inventoryId);

        InventoryItem item = inventoryItemRepository.findByIdAndTenantCodeAndInventoryId(itemId, tenantCode, inventoryId)
                .orElseThrow(() -> new RecordNotFoundException("Inventory item not found with id: " + itemId));

        log.info("Reorder request initiated for item: {} (SKU: {}) for tenant: {}", itemId, item.getSku(), tenantCode);
        // Here you would implement the notification logic (WhatsApp, Telegram, Email)
        // This could be done via a message queue or notification service
    }

    @Override
    @Transactional
    public void deleteInventoryItem(String tenantCode, Long inventoryId, Long itemId) {
        validateInventoryExists(tenantCode, inventoryId);

        InventoryItem item = inventoryItemRepository.findByIdAndTenantCodeAndInventoryId(itemId, tenantCode, inventoryId)
                .orElseThrow(() -> new RecordNotFoundException("Inventory item not found with id: " + itemId));

        inventoryItemRepository.delete(item);
        log.info("Deleted inventory item with id: {} for tenant: {}", itemId, tenantCode);
    }

    @Override
    public Page<InventoryItemResponse> searchInventoryItems(String tenantCode, Long inventoryId, String query,
                                                            String sku, String barcode, Boolean perishable, Boolean requiresRefrigeration, Pageable pageable) {
        validateInventoryExists(tenantCode, inventoryId);

        Page<InventoryItem> items = inventoryItemRepository.searchItems(
                tenantCode, inventoryId, query, sku, barcode, perishable, requiresRefrigeration, pageable);
        return items.map(inventoryItemMapper::toResponse);
    }

    private Inventory validateInventoryExists(String tenantCode, Long inventoryId) {
        return inventoryRepository.findByIdAndTenantCode(inventoryId, tenantCode)
                .orElseThrow(() -> new RecordNotFoundException("Inventory not found with id: " + inventoryId));
    }
}