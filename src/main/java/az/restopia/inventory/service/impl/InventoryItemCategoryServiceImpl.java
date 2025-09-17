package az.restopia.inventory.service.impl;

import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.inventory.domain.entity.InventoryItemCategory;
import az.restopia.inventory.domain.mapper.InventoryItemCategoryMapper;
import az.restopia.inventory.domain.request.InventoryItemCategoryRequest;
import az.restopia.inventory.domain.response.InventoryItemCategoryResponse;
import az.restopia.inventory.repository.InventoryItemCategoryRepository;
import az.restopia.inventory.service.InventoryItemCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryItemCategoryServiceImpl implements InventoryItemCategoryService {

    private final InventoryItemCategoryRepository repository;
    private final InventoryItemCategoryMapper mapper;

    @Override
    @Transactional
    public InventoryItemCategoryResponse create(String tenantCode, InventoryItemCategoryRequest request) {
        log.debug("Creating inventory item category for tenant: {}", tenantCode);

        if (existsByName(tenantCode, request.getName(), request.getParentId())) {
            throw new IllegalArgumentException("Category with this name already exists in the same parent category");
        }

        InventoryItemCategory entity = mapper.toEntity(request);
        entity.setTenantCode(tenantCode);

        if (request.getParentId() != null) {
            InventoryItemCategory parent = findEntityById(tenantCode, request.getParentId());
            entity.setParent(parent);
        }

        InventoryItemCategory savedEntity = repository.save(entity);
        log.debug("Created inventory item category with id: {}", savedEntity.getId());

        return mapper.toResponse(savedEntity);
    }

    @Override
    public InventoryItemCategoryResponse findById(String tenantCode, Long id) {
        log.debug("Finding inventory item category by id: {} for tenant: {}", id, tenantCode);

        InventoryItemCategory entity = findEntityById(tenantCode, id);
        return mapper.toResponse(entity);
    }

    @Override
    public Page<InventoryItemCategoryResponse> findAll(String tenantCode, Pageable pageable) {
        log.debug("Finding all inventory item categories for tenant: {}", tenantCode);

        Page<InventoryItemCategory> entities = repository.findByTenantCodeOrderBySortOrderAscNameAsc(tenantCode, pageable);
        return entities.map(mapper::toResponse);
    }

    @Override
    public List<InventoryItemCategoryResponse> findRootCategories(String tenantCode) {
        log.debug("Finding root categories for tenant: {}", tenantCode);

        List<InventoryItemCategory> entities = repository.findByTenantCodeAndParentIsNullOrderBySortOrderAscNameAsc(tenantCode);
        return entities.stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<InventoryItemCategoryResponse> findByParent(String tenantCode, Long parentId) {
        log.debug("Finding categories by parent: {} for tenant: {}", parentId, tenantCode);

        List<InventoryItemCategory> entities;
        if (parentId == null) {
            entities = repository.findByTenantCodeAndParentIsNullOrderBySortOrderAscNameAsc(tenantCode);
        } else {
            entities = repository.findByTenantCodeAndParentIdOrderBySortOrderAscNameAsc(tenantCode, parentId);
        }

        return entities.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public InventoryItemCategoryResponse update(String tenantCode, Long id, InventoryItemCategoryRequest request) {
        log.debug("Updating inventory item category with id: {} for tenant: {}", id, tenantCode);

        InventoryItemCategory entity = findEntityById(tenantCode, id);

        if (repository.existsByTenantCodeAndNameAndParentIdAndIdNot(tenantCode, request.getName(), request.getParentId(), id)) {
            throw new IllegalArgumentException("Category with this name already exists in the same parent category");
        }

        mapper.updateEntityFromRequest(request, entity);

        if (request.getParentId() != null) {
            if (request.getParentId().equals(id)) {
                throw new IllegalArgumentException("Category cannot be its own parent");
            }
            InventoryItemCategory parent = findEntityById(tenantCode, request.getParentId());
            entity.setParent(parent);
        } else {
            entity.setParent(null);
        }

        InventoryItemCategory savedEntity = repository.save(entity);
        log.debug("Updated inventory item category with id: {}", savedEntity.getId());

        return mapper.toResponse(savedEntity);
    }

    @Override
    @Transactional
    public void delete(String tenantCode, Long id) {
        log.debug("Deleting inventory item category with id: {} for tenant: {}", id, tenantCode);

        InventoryItemCategory entity = findEntityById(tenantCode, id);
        repository.delete(entity);

        log.debug("Deleted inventory item category with id: {}", id);
    }

    @Override
    public boolean existsByName(String tenantCode, String name, Long parentId) {
        return repository.existsByTenantCodeAndNameAndParentId(tenantCode, name, parentId);
    }

    private InventoryItemCategory findEntityById(String tenantCode, Long id) {
        return repository.findByIdAndTenantCode(id, tenantCode)
                .orElseThrow(() -> new RecordNotFoundException("Inventory item category not found with id: " + id));
    }
}