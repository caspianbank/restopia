package az.neotech.neoeats.menu.controller;

import az.neotech.neoeats.menu.domain.request.MenuItemRequest;
import az.neotech.neoeats.menu.domain.response.MenuItemResponse;
import az.neotech.neoeats.menu.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// todo: validation and swagger
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping
    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
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
