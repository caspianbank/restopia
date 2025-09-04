package az.restopia.menu.service;

import az.restopia.commons.domain.enums.SortBy;
import az.restopia.commons.domain.enums.SortOrder;
import az.restopia.menu.domain.entity.MenuItem;
import az.restopia.menu.domain.request.MenuItemRequest;
import az.restopia.menu.domain.response.MenuItemResponse;
import java.util.List;

public interface MenuItemService {
    List<MenuItemResponse> getAllMenuItems();

    List<MenuItemResponse> getAllMenuItems(SortBy sortBy, SortOrder sortOrder);

    MenuItem getMenuItemByIdOrThrowNotFound(Long menuItemId);

    MenuItemResponse getMenuItemById(Long id);

    MenuItemResponse createMenuItem(MenuItemRequest request);

    MenuItemResponse updateMenuItem(Long id, MenuItemRequest request);

    void deleteMenuItem(Long id);
}