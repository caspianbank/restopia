package az.neotech.neoeats.service;

import java.util.List;

import az.neotech.neoeats.domain.request.MenuRequest;
import az.neotech.neoeats.domain.response.MenuResponse;

public interface MenuService {

    List<MenuResponse> getAllMenus();

    MenuResponse getMenuById(Long id);

    MenuResponse createMenu(MenuRequest menuRequest);

    MenuResponse updateMenu(Long id, MenuRequest menuRequest);

    void deleteMenu(Long id);

    List<MenuResponse> searchMenus(String name, String category);
}
