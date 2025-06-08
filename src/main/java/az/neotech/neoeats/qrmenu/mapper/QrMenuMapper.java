package az.neotech.neoeats.qrmenu.mapper;

import az.neotech.neoeats.menu.domain.entity.Menu;
import az.neotech.neoeats.menu.domain.response.MenuCategoryResponse;
import az.neotech.neoeats.menu.domain.response.MenuItemResponse;
import az.neotech.neoeats.qrmenu.domain.dto.response.QrMenuResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QrMenuMapper {

    default QrMenuResponse toResponse(Menu menu, List<MenuCategoryResponse> categories, List<MenuItemResponse> items) {
        QrMenuResponse response = new QrMenuResponse();
        response.setMenuName(menu.getName());
        response.setMenuDescription(menu.getDescription());
        response.setCategories(categories);
        response.setItems(items);
        return response;
    }
}