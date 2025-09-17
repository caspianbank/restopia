package az.restopia.inventory.service.impl;

import az.restopia.business.domain.entity.TenantBusiness;
import az.restopia.business.domain.entity.TenantBusinessStore;
import az.restopia.business.repository.TenantBusinessRepository;
import az.restopia.business.repository.TenantBusinessStoreRepository;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.inventory.domain.entity.Inventory;
import az.restopia.inventory.domain.mapper.InventoryMapper;
import az.restopia.inventory.domain.request.InventoryRequest;
import az.restopia.inventory.domain.response.InventoryResponse;
import az.restopia.inventory.repository.InventoryRepository;
import az.restopia.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final TenantBusinessRepository tenantBusinessRepository;
    private final TenantBusinessStoreRepository tenantBusinessStoreRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> getAllInventories(Long businessId, Long storeId) {
        List<Inventory> inventories;
        
        if (storeId != null) {
            inventories = inventoryRepository.findByBusinessIdAndBusinessStoreId(businessId, storeId);
        } else {
            inventories = inventoryRepository.findByBusinessId(businessId);
        }
        
        return inventoryMapper.toResponseList(inventories);
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryResponse getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Inventory not found with id: " + id));
        
        return inventoryMapper.toResponse(inventory);
    }

    @Override
    public InventoryResponse createInventory(InventoryRequest request) {
        TenantBusiness business = tenantBusinessRepository.findById(request.getBusinessId())
                .orElseThrow(() -> new RecordNotFoundException("Business not found with id: " + request.getBusinessId()));

        TenantBusinessStore businessStore = null;
        if (request.getBusinessStoreId() != null) {
            businessStore = tenantBusinessStoreRepository.findById(request.getBusinessStoreId())
                    .orElseThrow(() -> new RecordNotFoundException("Business store not found with id: " + request.getBusinessStoreId()));
        }

        if (request.isMain()) {
            validateMainInventoryConstraint(request.getBusinessId(), request.getBusinessStoreId());
        }

        Inventory inventory = inventoryMapper.toEntity(request);
        inventory.setBusiness(business);
        inventory.setBusinessStore(businessStore);

        Inventory savedInventory = inventoryRepository.save(inventory);
        log.info("Created inventory with id: {}", savedInventory.getId());
        
        return inventoryMapper.toResponse(savedInventory);
    }

    @Override
    public InventoryResponse updateInventory(Long id, InventoryRequest request) {
        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Inventory not found with id: " + id));

        if (!existingInventory.isMain()) {
            validateMainInventoryConstraint(request.getBusinessId(), request.getBusinessStoreId());
        }

        TenantBusiness business = tenantBusinessRepository.findById(request.getBusinessId())
                .orElseThrow(() -> new RecordNotFoundException("Business not found with id: " + request.getBusinessId()));

        TenantBusinessStore businessStore = null;
        if (request.getBusinessStoreId() != null) {
            businessStore = tenantBusinessStoreRepository.findById(request.getBusinessStoreId())
                    .orElseThrow(() -> new RecordNotFoundException("Business store not found with id: " + request.getBusinessStoreId()));
        }

        inventoryMapper.updateEntity(request, existingInventory);
        existingInventory.setBusiness(business);
        existingInventory.setBusinessStore(businessStore);

        Inventory updatedInventory = inventoryRepository.save(existingInventory);
        log.info("Updated inventory with id: {}", updatedInventory.getId());
        
        return inventoryMapper.toResponse(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Inventory not found with id: " + id));

        inventoryRepository.delete(inventory);
        log.info("Deleted inventory with id: {}", id);
    }

    private void validateMainInventoryConstraint(Long businessId, Long storeId) {
        if (storeId == null) {
            Optional<Inventory> existingMainInventory = inventoryRepository.findMainBusinessInventory(businessId);
            if (existingMainInventory.isPresent()) {
                throw new IllegalArgumentException("A main inventory already exists for this business");
            }
        } else {
            Optional<Inventory> existingMainInventory = inventoryRepository.findMainStoreInventory(storeId);
            if (existingMainInventory.isPresent()) {
                throw new IllegalArgumentException("A main inventory already exists for this store");
            }
        }
    }
}