package az.neotech.neoeats.qrmenu.service.impl;

import az.neotech.neoeats.commons.enums.DeleteStatus;
import az.neotech.neoeats.commons.exception.RecordNotFoundException;
import az.neotech.neoeats.layout.domain.entity.RestaurantTable;
import az.neotech.neoeats.layout.repository.RestaurantTableRepository;
import az.neotech.neoeats.menu.domain.entity.Menu;
import az.neotech.neoeats.menu.domain.mapper.MenuCategoryMapper;
import az.neotech.neoeats.menu.domain.mapper.MenuItemMapper;
import az.neotech.neoeats.menu.domain.response.MenuCategoryResponse;
import az.neotech.neoeats.menu.domain.response.MenuItemResponse;
import az.neotech.neoeats.qrmenu.domain.dto.response.QrMenuResponse;
import az.neotech.neoeats.qrmenu.mapper.QrMenuMapper;
import az.neotech.neoeats.qrmenu.service.QrMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import az.neotech.neoeats.menu.domain.entity.MenuCategory;
import az.neotech.neoeats.menu.domain.entity.MenuItem;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QrMenuServiceImpl implements QrMenuService {

    private final RestaurantTableRepository tableRepository;
    private final MenuCategoryMapper menuCategoryMapper;
    private final MenuItemMapper menuItemMapper;
    private final QrMenuMapper qrMenuMapper;

    @Override
    public QrMenuResponse getMenuByQrCode(String qrCode) {
        RestaurantTable table = tableRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RecordNotFoundException("Table not found with QR code: " + qrCode));

        Menu menu = table.getMenu();

        List<MenuCategoryResponse> categories = menu.getCategories().stream()
                .filter(MenuCategory::isActive)
                .filter(cat -> cat.getDeleteStatus() == DeleteStatus.ACTIVE)
                .map(menuCategoryMapper::toResponse)
                .collect(Collectors.toList());

        List<MenuItemResponse> items = menu.getCategories().stream()
                .flatMap(category -> category.getItems().stream())
                .filter(MenuItem::isActive)
                .filter(item -> item.getDeleteStatus() == DeleteStatus.ACTIVE)
                .map(menuItemMapper::toMenuItemResponse)
                .collect(Collectors.toList());

        return qrMenuMapper.toResponse(menu, categories, items);
    }
}
