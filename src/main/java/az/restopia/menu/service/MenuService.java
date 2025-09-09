package az.restopia.menu.service;

import az.restopia.menu.domain.request.MenuRequest;
import az.restopia.menu.domain.response.MenuResponse;

import java.util.List;

public interface MenuService {

    List<MenuResponse> getAllMenus();

    MenuResponse getMenuById(Long id);

    MenuResponse getActiveMenu(String tenantCode);

    MenuResponse createMenu(MenuRequest menuRequest);

    MenuResponse updateMenu(Long id, MenuRequest menuRequest);

    void deleteMenu(Long id);

    List<MenuResponse> searchMenus(String name, String category);
}
