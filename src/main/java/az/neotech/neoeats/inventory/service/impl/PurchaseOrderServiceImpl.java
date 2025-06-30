package az.neotech.neoeats.inventory.service.impl;

import az.neotech.neoeats.commons.domain.enums.DeleteStatus;
import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import az.neotech.neoeats.inventory.domain.entity.PurchaseOrder;
import az.neotech.neoeats.inventory.domain.mapper.PurchaseOrderMapper;
import az.neotech.neoeats.inventory.domain.request.PurchaseOrderRequest;
import az.neotech.neoeats.inventory.domain.response.PurchaseOrderResponse;
import az.neotech.neoeats.inventory.repository.PurchaseOrderRepository;
import az.neotech.neoeats.inventory.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseOrderResponse> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll().stream()
                .map(purchaseOrderMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderResponse getPurchaseOrderById(Long id) {
        log.info("Fetching PurchaseOrder with ID: {}", id);

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("PurchaseOrder with ID: {} not found", id);
                    return new RecordNotFoundException("PurchaseOrder not found");
                });

        return purchaseOrderMapper.toResponse(purchaseOrder);
    }

    @Override
    @Transactional
    public PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request) {
        PurchaseOrder purchaseOrder = purchaseOrderMapper.toEntity(request);
        PurchaseOrder saved = purchaseOrderRepository.save(purchaseOrder);

        log.info("PurchaseOrder created with ID: {}", saved.getId());
        return purchaseOrderMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public PurchaseOrderResponse updatePurchaseOrder(Long id, PurchaseOrderRequest request) {
        log.info("Updating PurchaseOrder with ID: {}", id);

        PurchaseOrder existing = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("PurchaseOrder with ID: {} not found for update", id);
                    return new RecordNotFoundException("PurchaseOrder not found");
                });

        purchaseOrderMapper.updateEntity(existing, request);
        PurchaseOrder updated = purchaseOrderRepository.save(existing);

        log.info("PurchaseOrder with ID: {} successfully updated", updated.getId());
        return purchaseOrderMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deletePurchaseOrder(Long id) {
        log.info("Deleting PurchaseOrder with ID: {}", id);

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("PurchaseOrder with ID: {} not found for deletion", id);
                    return new RecordNotFoundException("PurchaseOrder not found");
                });

        purchaseOrder.setDeleteStatus(DeleteStatus.DELETED);
        purchaseOrderRepository.save(purchaseOrder);

        log.info("PurchaseOrder with ID: {} marked as deleted", id);
    }
}
