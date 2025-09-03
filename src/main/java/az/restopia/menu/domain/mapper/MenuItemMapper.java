package az.restopia.menu.domain.mapper;

import az.restopia.menu.domain.entity.MenuItem;
import az.restopia.menu.domain.request.MenuItemRequest;
import az.restopia.menu.domain.response.MenuItemResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    MenuItem toMenuItem(MenuItemRequest request);

    MenuItemResponse toMenuItemResponse(MenuItem menuItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMenuItemFromRequest(MenuItemRequest request, @MappingTarget MenuItem menuItem);
}