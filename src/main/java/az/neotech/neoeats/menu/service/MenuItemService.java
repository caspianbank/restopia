package az.neotech.neoeats.menu.service;

import az.neotech.neoeats.menu.domain.request.MenuItemRequest;
import az.neotech.neoeats.menu.domain.response.MenuItemResponse;

import java.util.List;

public interface MenuItemService {
    List<MenuItemResponse> getAllMenuItems();

    MenuItemResponse getMenuItemById(Long id);

    MenuItemResponse createMenuItem(MenuItemRequest request);

    MenuItemResponse updateMenuItem(Long id, MenuItemRequest request);

    void deleteMenuItem(Long id);
}