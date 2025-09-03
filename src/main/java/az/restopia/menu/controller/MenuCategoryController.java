package az.restopia.menu.controller;

import az.restopia.menu.domain.request.MenuCategoryRequest;
import az.restopia.menu.domain.response.MenuCategoryResponse;
import az.restopia.menu.service.MenuCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// todo: validation and swagger
@RestController
@RequestMapping("/api/v1/menu-category")
@RequiredArgsConstructor
public class MenuCategoryController {

    private final MenuCategoryService menuCategoryService;

    @GetMapping
    public List<MenuCategoryResponse> getAllMenuCategories() {
        return menuCategoryService.getAllMenuCategories();
    }

    @GetMapping("/{id}")
    public MenuCategoryResponse getMenuCategoryById(@PathVariable Long id) {
        return menuCategoryService.getMenuCategoryById(id);
    }

    @PostMapping
    public MenuCategoryResponse createMenuCategory(@RequestBody MenuCategoryRequest request) {
        return menuCategoryService.createMenuCategory(request);
    }

    @PutMapping("/{id}")
    public MenuCategoryResponse updateMenuCategory(@PathVariable Long id, @RequestBody MenuCategoryRequest request) {
        return menuCategoryService.updateMenuCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuCategory(@PathVariable Long id) {
        menuCategoryService.deleteMenuCategory(id);
    }
}