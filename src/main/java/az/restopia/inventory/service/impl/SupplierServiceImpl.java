package az.restopia.inventory.service.impl;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.DuplicateResourceException;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.inventory.domain.entity.Supplier;
import az.restopia.inventory.domain.mapper.SupplierMapper;
import az.restopia.inventory.domain.request.SupplierRequest;
import az.restopia.inventory.domain.response.SupplierResponse;
import az.restopia.inventory.repository.SupplierRepository;
import az.restopia.inventory.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public Page<SupplierResponse> getAllSuppliers(Pageable pageable) {
        log.info("Fetching all non-deleted suppliers with pagination: {}", pageable);
        Page<Supplier> suppliers = supplierRepository.findByDeleteStatusNot(DeleteStatus.DELETED, pageable);
        log.debug("Found {} suppliers", suppliers.getTotalElements());
        return suppliers.map(supplierMapper::toResponse);
    }

    @Override
    public SupplierResponse getSupplierById(Long id) {
        log.info("Fetching supplier with id={}", id);
        Supplier supplier = supplierRepository.findByIdAndDeleteStatusNot(id, DeleteStatus.DELETED)
                .orElseThrow(() -> {
                    log.warn("Supplier with id={} not found", id);
                    return new RecordNotFoundException("Supplier not found with id: " + id);
                });
        log.debug("Found supplier: {}", supplier.getName());
        return supplierMapper.toResponse(supplier);
    }

    @Override
    @Transactional
    public SupplierResponse createSupplier(SupplierRequest request) {
        log.info("Creating supplier with tenantCode={} and name={}", request.tenantCode(), request.name());

        validateSupplierRequest(request);
        checkForDuplicates(request, null);

        Supplier supplier = supplierMapper.toEntity(request);
        supplier.setDeleteStatus(DeleteStatus.ACTIVE);

        Supplier saved = supplierRepository.save(supplier);
        log.debug("Created supplier with id={}", saved.getId());
        return supplierMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public SupplierResponse updateSupplier(Long id, SupplierRequest request) {
        log.info("Updating supplier with id={}", id);

        validateSupplierRequest(request);

        Supplier supplier = supplierRepository.findByIdAndDeleteStatusNot(id, DeleteStatus.DELETED)
                .orElseThrow(() -> {
                    log.warn("Supplier with id={} not found for update", id);
                    return new RecordNotFoundException("Supplier not found with id: " + id);
                });

        checkForDuplicates(request, id);

        supplierMapper.update(supplier, request);
        Supplier updated = supplierRepository.save(supplier);
        log.debug("Updated supplier with id={}", updated.getId());
        return supplierMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteSupplier(Long id) {
        log.info("Soft deleting supplier with id={}", id);

        Supplier supplier = supplierRepository.findByIdAndDeleteStatusNot(id, DeleteStatus.DELETED)
                .orElseThrow(() -> {
                    log.warn("Supplier with id={} not found for deletion", id);
                    return new RecordNotFoundException("Supplier not found with id: " + id);
                });

        supplier.setDeleteStatus(DeleteStatus.DELETED);
        supplierRepository.save(supplier);
        log.debug("Soft deleted supplier with id={}", id);
    }

    @Override
    public Page<SupplierResponse> searchSuppliersByName(String name, Pageable pageable) {
        if (!StringUtils.hasText(name)) {
            log.warn("Search name is null or empty");
            throw new IllegalArgumentException("Search name cannot be null or empty");
        }

        log.info("Searching suppliers by name: {}", name);
        Page<Supplier> suppliers = supplierRepository.findByNameContainingIgnoreCaseAndDeleteStatusNot(
                name.trim(), DeleteStatus.DELETED, pageable);
        log.debug("Found {} suppliers matching name: {}", suppliers.getTotalElements(), name);
        return suppliers.map(supplierMapper::toResponse);
    }

    private void validateSupplierRequest(SupplierRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Supplier request cannot be null");
        }

        if (!StringUtils.hasText(request.name())) {
            throw new IllegalArgumentException("Supplier name is required");
        }

        if (!StringUtils.hasText(request.tenantCode())) {
            throw new IllegalArgumentException("Tenant code is required");
        }

        // Add more validation rules as needed
        if (StringUtils.hasText(request.email()) && !isValidEmail(request.email())) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    /**
     * Checks for duplicate suppliers based on email or phone.
     *
     * @param request   the supplier request
     * @param excludeId the ID to exclude from duplicate check (for updates)
     * @throws DuplicateResourceException if duplicate found
     */
    private void checkForDuplicates(SupplierRequest request, Long excludeId) {
        // Check email duplicate
        if (StringUtils.hasText(request.email())) {
            boolean emailExists = excludeId != null
                    ? supplierRepository.existsByEmailAndDeleteStatusNotAndIdNot(request.email().trim(), DeleteStatus.DELETED, excludeId)
                    : supplierRepository.existsByEmailAndDeleteStatusNot(request.email().trim(), DeleteStatus.DELETED);

            if (emailExists) {
                throw new DuplicateResourceException("Supplier with email already exists: " + request.email());
            }
        }

        // Check phone duplicate
        if (StringUtils.hasText(request.phones())) {
            boolean phonesExists = excludeId != null
                    ? supplierRepository.existsByPhonesAndDeleteStatusNotAndIdNot(request.phones().trim(), DeleteStatus.DELETED, excludeId)
                    : supplierRepository.existsByPhonesAndDeleteStatusNot(request.phones().trim(), DeleteStatus.DELETED);

            if (phonesExists) {
                throw new DuplicateResourceException("Supplier with phone number already exists: " + request.phones());
            }
        }
    }


    /**
     * Validates email format.
     *
     * @param email the email to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    }
}
