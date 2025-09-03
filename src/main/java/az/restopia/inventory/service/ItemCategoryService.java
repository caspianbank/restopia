package az.restopia.inventory.service;

import az.restopia.inventory.domain.request.ItemCategoryRequest;
import az.restopia.inventory.domain.response.ItemCategoryResponse;

import java.util.List;

public interface ItemCategoryService {
    List<ItemCategoryResponse> getAllItemCategories();

    ItemCategoryResponse getItemCategoryById(Long id);

    ItemCategoryResponse createItemCategory(ItemCategoryRequest request);

    ItemCategoryResponse updateItemCategory(Long id, ItemCategoryRequest request);

    void deleteItemCategory(Long id);
}
