package az.neotech.neoeats.menu.service;

import az.neotech.neoeats.menu.domain.request.MenuRequest;
import az.neotech.neoeats.menu.domain.response.MenuResponse;

import java.util.List;

public interface MenuService {

    List<MenuResponse> getAllMenus();

    MenuResponse getMenuById(Long id);

    MenuResponse createMenu(MenuRequest menuRequest);

    MenuResponse updateMenu(Long id, MenuRequest menuRequest);

    void deleteMenu(Long id);

    List<MenuResponse> searchMenus(String name, String category);
}
