package az.neotech.neoeats.inventory.service.impl;

import az.neotech.neoeats.commons.domain.enums.DeleteStatus;
import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import az.neotech.neoeats.inventory.domain.entity.InventoryItem;
import az.neotech.neoeats.inventory.domain.mapper.InventoryItemMapper;
import az.neotech.neoeats.inventory.domain.request.InventoryItemRequest;
import az.neotech.neoeats.inventory.domain.response.InventoryItemResponse;
import az.neotech.neoeats.inventory.repository.InventoryItemRepository;
import az.neotech.neoeats.inventory.service.InventoryItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

// todo: find by id in separate method
// todo: replace duplicate log statements with constants or methods
@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryItemServiceImpl implements InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemMapper inventoryItemMapper;

    @Override
    public InventoryItemResponse createInventoryItem(InventoryItemRequest request) {
        log.info("Creating InventoryItem with name={} and tenantCode={}", request.name(), request.tenantCode());
        InventoryItem item = inventoryItemMapper.toEntity(request);
        InventoryItem saved = inventoryItemRepository.save(item);
        log.debug("Created InventoryItem with id={}", saved.getId());
        return inventoryItemMapper.toResponse(saved);
    }

    @Override
    public InventoryItemResponse updateInventoryItem(Long id, InventoryItemRequest request) {
        log.info("Updating InventoryItem with id={}", id);
        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("InventoryItem with id={} not found", id);
                    return new RecordNotFoundException("InventoryItem not found");
                });

        inventoryItemMapper.update(item, request);
        InventoryItem updated = inventoryItemRepository.save(item);
        log.debug("Updated InventoryItem with id={}", updated.getId());
        return inventoryItemMapper.toResponse(updated);
    }

    @Override
    public InventoryItemResponse getInventoryItemById(Long id) {
        log.info("Fetching InventoryItem by id={}", id);
        return inventoryItemRepository.findById(id)
                .map(inventoryItemMapper::toResponse)
                .orElseThrow(() -> {
                    log.warn("InventoryItem with id={} not found", id);
                    return new RecordNotFoundException("InventoryItem not found");
                });
    }

    @Override
    public List<InventoryItemResponse> getAllInventoryItems() {
        log.info("Fetching all InventoryItems");
        List<InventoryItem> items = inventoryItemRepository.findAll().stream()
                .toList();
        log.debug("Found {} InventoryItems", items.size());
        return items.stream().map(inventoryItemMapper::toResponse).toList();
    }

    @Override
    public void deleteInventoryItem(Long id) {
        log.info("Deleting InventoryItem with id={}", id);
        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("InventoryItem with id={} not found", id);
                    return new RecordNotFoundException("InventoryItem not found");
                });

        item.setDeleteStatus(DeleteStatus.DELETED);
        inventoryItemRepository.save(item);
        log.debug("Deleted InventoryItem with id={}", id);
    }
}
