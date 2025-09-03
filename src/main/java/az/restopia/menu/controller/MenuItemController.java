package az.restopia.menu.controller;

import az.restopia.commons.domain.enums.SortBy;
import az.restopia.commons.domain.enums.SortOrder;
import az.restopia.menu.domain.request.MenuItemRequest;
import az.restopia.menu.domain.response.MenuItemResponse;
import az.restopia.menu.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping
    public List<MenuItemResponse> getAllMenuItems(
            @RequestParam(required = false) SortBy sortBy,
            @RequestParam(required = false) SortOrder sortOrder
    ) {
        return menuItemService.getAllMenuItems(
                sortBy != null ? sortBy : SortBy.POSITION,
                sortOrder != null ? sortOrder : SortOrder.ASCENDING
        );
    }

    @GetMapping("/{id}")
    public MenuItemResponse getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id);
    }

    @PostMapping
    public MenuItemResponse createMenuItem(@RequestBody MenuItemRequest request) {
        return menuItemService.createMenuItem(request);
    }

    @PutMapping("/{id}")
    public MenuItemResponse updateMenuItem(@PathVariable Long id, @RequestBody MenuItemRequest request) {
        return menuItemService.updateMenuItem(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
    }
}
