package az.restopia.inventory.service.impl;

import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.inventory.domain.entity.InventoryItem;
import az.restopia.inventory.domain.entity.InventoryWastage;
import az.restopia.inventory.domain.enums.WastageStatus;
import az.restopia.inventory.domain.mapper.InventoryWastageMapper;
import az.restopia.inventory.domain.request.InventoryWastageRequest;
import az.restopia.inventory.domain.response.InventoryWastageResponse;
import az.restopia.inventory.repository.InventoryItemRepository;
import az.restopia.inventory.repository.InventoryWastageRepository;
import az.restopia.inventory.service.InventoryWastageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryWastageServiceImpl implements InventoryWastageService {

    private final InventoryWastageRepository inventoryWastageRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryWastageMapper inventoryWastageMapper;

    @Override
    public Page<InventoryWastageResponse> getAllWastage(String tenantCode, Pageable pageable) {
        return inventoryWastageRepository.findByTenantCode(tenantCode, pageable)
                .map(inventoryWastageMapper::toResponse);
    }

    @Override
    public InventoryWastageResponse getWastageById(String tenantCode, Long id) {
        InventoryWastage wastage = findWastageByIdAndTenant(tenantCode, id);
        return inventoryWastageMapper.toResponse(wastage);
    }

    @Override
    @Transactional
    public InventoryWastageResponse createWastage(String tenantCode, InventoryWastageRequest request) {
        InventoryItem inventoryItem = findInventoryItemByIdAndTenant(tenantCode, request.getInventoryItemId());

        InventoryWastage wastage = inventoryWastageMapper.toEntity(request);
        wastage.setTenantCode(tenantCode);
        wastage.setInventoryItem(inventoryItem);
        wastage.setWastageReference(generateWastageReference(tenantCode));
        wastage.setStatus(WastageStatus.REPORTED);

        if (request.getUnitCostAtWastage() != null && request.getWastedQuantity() != null) {
            BigDecimal totalLoss = request.getWastedQuantity().multiply(request.getUnitCostAtWastage());
            wastage.setTotalLossAmount(totalLoss);
        }

        InventoryWastage savedWastage = inventoryWastageRepository.save(wastage);
        log.info("Created wastage with reference: {} for tenant: {}", savedWastage.getWastageReference(), tenantCode);

        return inventoryWastageMapper.toResponse(savedWastage);
    }

    @Override
    @Transactional
    public InventoryWastageResponse updateWastage(String tenantCode, Long id, InventoryWastageRequest request) {
        InventoryWastage existingWastage = findWastageByIdAndTenant(tenantCode, id);

        if (request.getInventoryItemId() != null &&
                !request.getInventoryItemId().equals(existingWastage.getInventoryItem().getId())) {
            InventoryItem inventoryItem = findInventoryItemByIdAndTenant(tenantCode, request.getInventoryItemId());
            existingWastage.setInventoryItem(inventoryItem);
        }

        inventoryWastageMapper.updateEntity(request, existingWastage);

        if (request.getUnitCostAtWastage() != null && request.getWastedQuantity() != null) {
            BigDecimal totalLoss = request.getWastedQuantity().multiply(request.getUnitCostAtWastage());
            existingWastage.setTotalLossAmount(totalLoss);
        }

        InventoryWastage updatedWastage = inventoryWastageRepository.save(existingWastage);
        log.info("Updated wastage with id: {} for tenant: {}", id, tenantCode);

        return inventoryWastageMapper.toResponse(updatedWastage);
    }

    @Override
    @Transactional
    public void deleteWastage(String tenantCode, Long id) {
        InventoryWastage wastage = findWastageByIdAndTenant(tenantCode, id);
        inventoryWastageRepository.delete(wastage);
        log.info("Deleted wastage with id: {} for tenant: {}", id, tenantCode);
    }

    @Override
    public Page<InventoryWastageResponse> searchWastage(String tenantCode, String reference, String reason,
                                                        String status, String location, Long inventoryItemId, Pageable pageable) {
        return inventoryWastageRepository.searchWastages(tenantCode, reference, reason, status, location, inventoryItemId, pageable)
                .map(inventoryWastageMapper::toResponse);
    }

    @Override
    @Transactional
    public InventoryWastageResponse approveWastage(String tenantCode, Long id, String approvedBy, Long approvedById) {
        InventoryWastage wastage = findWastageByIdAndTenant(tenantCode, id);

        wastage.setApprovedBy(approvedBy);
        wastage.setApprovedById(approvedById);
        wastage.setApprovedDate(LocalDateTime.now());
        wastage.setStatus(WastageStatus.APPROVED);

        InventoryWastage approvedWastage = inventoryWastageRepository.save(wastage);
        log.info("Approved wastage with id: {} by {} for tenant: {}", id, approvedBy, tenantCode);

        return inventoryWastageMapper.toResponse(approvedWastage);
    }

    private InventoryWastage findWastageByIdAndTenant(String tenantCode, Long id) {
        return inventoryWastageRepository.findByIdAndTenantCode(id, tenantCode)
                .orElseThrow(() -> new RecordNotFoundException("Inventory wastage not found with id: " + id));
    }

    private InventoryItem findInventoryItemByIdAndTenant(String tenantCode, Long inventoryItemId) {
        return inventoryItemRepository.findByIdAndTenantCode(inventoryItemId, tenantCode)
                .orElseThrow(() -> new RecordNotFoundException("Inventory item not found with id: " + inventoryItemId));
    }

    private String generateWastageReference(String tenantCode) {
        long count = inventoryWastageRepository.countByTenantCode(tenantCode) + 1;
        return String.format("WASTE-%s-%04d", tenantCode, count);
    }
}