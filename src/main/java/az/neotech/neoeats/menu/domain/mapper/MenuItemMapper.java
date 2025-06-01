package az.neotech.neoeats.menu.domain.mapper;

import az.neotech.neoeats.menu.domain.entity.MenuItem;
import az.neotech.neoeats.menu.domain.request.MenuItemRequest;
import az.neotech.neoeats.menu.domain.response.MenuItemResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    MenuItem toMenuItem(MenuItemRequest request);

    MenuItemResponse toMenuItemResponse(MenuItem menuItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMenuItemFromRequest(MenuItemRequest request, @MappingTarget MenuItem menuItem);
}