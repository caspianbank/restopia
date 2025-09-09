package az.restopia.menu.service.impl;

import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.menu.domain.entity.Menu;
import az.restopia.menu.domain.request.MenuRequest;
import az.restopia.menu.domain.response.MenuResponse;
import az.restopia.menu.repository.MenuRepository;
import az.restopia.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public List<MenuResponse> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .map(this::toMenuResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MenuResponse getMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id " + id));
        return toMenuResponse(menu);
    }

    @Override
    public MenuResponse getActiveMenu(String tenantCode) {
        return menuRepository.findActiveMenuByTenantCode(tenantCode)
                .map(this::toMenuResponse)
                .orElseThrow(() -> new RecordNotFoundException("Menu not found with tenant code " + tenantCode));
    }

    @Override
    @Transactional
    public MenuResponse createMenu(MenuRequest menuRequest) {
        Menu menu = toMenu(menuRequest);
        Menu saved = menuRepository.save(menu);
        return toMenuResponse(saved);
    }

    @Override
    public MenuResponse updateMenu(Long id, MenuRequest menuRequest) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id " + id));
        menu.setName(menuRequest.getName());
        menu.setDescription(menuRequest.getDescription());
        // Update items if applicable
        Menu updated = menuRepository.save(menu);
        return toMenuResponse(updated);
    }

    @Override
    public void deleteMenu(Long id) {
        if (!menuRepository.existsById(id)) {
            throw new RuntimeException("Menu not found with id " + id);
        }
        menuRepository.deleteById(id);
    }

    @Override
    public List<MenuResponse> searchMenus(String name, String category) {
        List<Menu> menus;
        if (name != null && !name.isEmpty()) {
            menus = menuRepository.findByNameContainingIgnoreCase(name);
        } else {
            menus = menuRepository.findAll();
        }
        // Optionally filter by category here if implemented
        return menus.stream()
                .map(this::toMenuResponse)
                .collect(Collectors.toList());
    }

    private MenuResponse toMenuResponse(Menu menu) {
        return new MenuResponse(
                menu.getName(),
                menu.getDescription(),
                menu.isActive()
        );
    }

    private Menu toMenu(MenuRequest request) {
        Menu menu = new Menu();
        menu.setName(request.getName());
        menu.setDescription(request.getDescription());
        // Map items if any
        return menu;
    }

}
