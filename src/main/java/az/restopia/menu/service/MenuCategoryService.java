package az.restopia.menu.service;

import az.restopia.menu.domain.request.MenuCategoryRequest;
import az.restopia.menu.domain.response.MenuCategoryResponse;

import java.util.List;

public interface MenuCategoryService {

    List<MenuCategoryResponse> getAllMenuCategories();

    MenuCategoryResponse getMenuCategoryById(Long id);

    MenuCategoryResponse createMenuCategory(MenuCategoryRequest request);

    MenuCategoryResponse updateMenuCategory(Long id, MenuCategoryRequest request);

    void deleteMenuCategory(Long id);
}
