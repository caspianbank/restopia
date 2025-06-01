package az.neotech.neoeats.inventory.service.impl;

import az.neotech.neoeats.commons.enums.DeleteStatus;
import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import az.neotech.neoeats.inventory.domain.entity.Supplier;
import az.neotech.neoeats.inventory.domain.mapper.SupplierMapper;
import az.neotech.neoeats.inventory.domain.request.SupplierRequest;
import az.neotech.neoeats.inventory.domain.response.SupplierResponse;
import az.neotech.neoeats.inventory.repository.SupplierRepository;
import az.neotech.neoeats.inventory.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

// todo: find by id method in separate method
@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public List<SupplierResponse> getAllSuppliers() {
        log.info("Fetching all non-deleted suppliers");
        List<Supplier> suppliers = supplierRepository.findAll();
        log.debug("Found {} suppliers", suppliers.size());
        return suppliers.stream()
                .map(supplierMapper::toResponse)
                .toList();
    }

    @Override
    public SupplierResponse createSupplier(SupplierRequest request) {
        log.info("Creating supplier with tenantCode={} and name={}", request.tenantCode(), request.name());
        Supplier supplier = supplierMapper.toEntity(request);
        Supplier saved = supplierRepository.save(supplier);
        log.debug("Created supplier with id={}", saved.getId());
        return supplierMapper.toResponse(saved);
    }

    @Override
    public SupplierResponse updateSupplier(Long id, SupplierRequest request) {
        log.info("Updating supplier with id={}", id);
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> {
            log.warn("Supplier with id={} not found for update", id);
            return new RecordNotFoundException("Supplier not found");
        });
        supplierMapper.update(supplier, request);
        Supplier updated = supplierRepository.save(supplier);
        log.debug("Updated supplier with id={}", updated.getId());
        return supplierMapper.toResponse(updated);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Supplier with id={} not found for deletion", id);
                    return new RecordNotFoundException("Supplier not found");
                });
        supplier.setDeleteStatus(DeleteStatus.DELETED);
        supplierRepository.save(supplier);
        log.debug("Deleted supplier with id={}", id);
    }
}
