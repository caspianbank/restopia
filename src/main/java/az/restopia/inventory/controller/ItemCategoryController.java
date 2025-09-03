package az.restopia.inventory.controller;

import az.restopia.inventory.domain.request.ItemCategoryRequest;
import az.restopia.inventory.domain.response.ItemCategoryResponse;
import az.restopia.inventory.service.ItemCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// todo: validation and swagger
@RestController
@RequestMapping("/api/v1/item-categories")
@RequiredArgsConstructor
public class ItemCategoryController {

    private final ItemCategoryService itemCategoryService;

    @GetMapping
    public List<ItemCategoryResponse> getAllItemCategories() {
        return itemCategoryService.getAllItemCategories();
    }

    @GetMapping("/{id}")
    public ItemCategoryResponse getItemCategoryById(@PathVariable Long id) {
        return itemCategoryService.getItemCategoryById(id);
    }

    @PostMapping
    public ItemCategoryResponse createItemCategory(@RequestBody @Valid ItemCategoryRequest request) {
        return itemCategoryService.createItemCategory(request);
    }

    @PutMapping("/{id}")
    public ItemCategoryResponse updateItemCategory(@PathVariable Long id,
                                                   @RequestBody @Valid ItemCategoryRequest request) {
        return itemCategoryService.updateItemCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteItemCategory(@PathVariable Long id) {
        itemCategoryService.deleteItemCategory(id);
    }
}