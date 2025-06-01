package az.neotech.neoeats.menu.service.impl;

import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import az.neotech.neoeats.menu.domain.entity.MenuCategory;
import az.neotech.neoeats.menu.domain.entity.MenuItem;
import az.neotech.neoeats.menu.domain.mapper.MenuItemMapper;
import az.neotech.neoeats.menu.domain.request.MenuItemRequest;
import az.neotech.neoeats.menu.domain.response.MenuItemResponse;
import az.neotech.neoeats.menu.repository.MenuCategoryRepository;
import az.neotech.neoeats.menu.repository.MenuItemRepository;
import az.neotech.neoeats.menu.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuCategoryRepository menuCategoryRepository;
    private final MenuItemMapper menuItemMapper;

    @Override
    public List<MenuItemResponse> getAllMenuItems() {
        log.info("Fetching all menu items");
        return menuItemRepository.findAll().stream()
                .map(menuItemMapper::toMenuItemResponse)
                .toList();
    }

    @Override
    public MenuItemResponse getMenuItemById(Long id) {
        log.info("Fetching menu item by id: {}", id);
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Menu item not found"));
        return menuItemMapper.toMenuItemResponse(menuItem);
    }

    @Override
    public MenuItemResponse createMenuItem(MenuItemRequest request) {
        log.info("Creating menu item with name: {}", request.getName());
        MenuCategory category = menuCategoryRepository.findById(request.getMenuCategoryId())
                .orElseThrow(() -> new RecordNotFoundException("Menu category not found"));
        MenuItem menuItem = menuItemMapper.toMenuItem(request);
        menuItem.setMenuCategory(category);
        return menuItemMapper.toMenuItemResponse(menuItemRepository.save(menuItem));
    }

    @Override
    public MenuItemResponse updateMenuItem(Long id, MenuItemRequest request) {
        log.info("Updating menu item with id: {}", id);
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Menu item not found"));

        if (request.getMenuCategoryId() != null) {
            MenuCategory category = menuCategoryRepository.findById(request.getMenuCategoryId())
                    .orElseThrow(() -> new RecordNotFoundException("Menu category not found"));
            menuItem.setMenuCategory(category);
        }

        menuItemMapper.updateMenuItemFromRequest(request, menuItem);
        return menuItemMapper.toMenuItemResponse(menuItemRepository.save(menuItem));
    }

    @Override
    public void deleteMenuItem(Long id) {
        log.info("Deleting menu item with id: {}", id);
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Menu item not found"));
        menuItemRepository.delete(menuItem);
    }
}
