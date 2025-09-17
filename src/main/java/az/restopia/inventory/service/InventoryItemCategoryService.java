package az.restopia.inventory.service;

import az.restopia.inventory.domain.request.InventoryItemCategoryRequest;
import az.restopia.inventory.domain.response.InventoryItemCategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InventoryItemCategoryService {

    InventoryItemCategoryResponse create(String tenantCode, InventoryItemCategoryRequest request);

    InventoryItemCategoryResponse findById(String tenantCode, Long id);

    Page<InventoryItemCategoryResponse> findAll(String tenantCode, Pageable pageable);

    List<InventoryItemCategoryResponse> findRootCategories(String tenantCode);

    List<InventoryItemCategoryResponse> findByParent(String tenantCode, Long parentId);

    InventoryItemCategoryResponse update(String tenantCode, Long id, InventoryItemCategoryRequest request);

    void delete(String tenantCode, Long id);

    boolean existsByName(String tenantCode, String name, Long parentId);
}