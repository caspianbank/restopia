package az.neotech.neoeats.menu.domain.mapper;

import az.neotech.neoeats.menu.domain.entity.MenuCategory;
import az.neotech.neoeats.menu.domain.request.MenuCategoryRequest;
import az.neotech.neoeats.menu.domain.response.MenuCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuCategoryMapper {

    @Mapping(source = "menuId", target = "menu.id")
    MenuCategory toEntity(MenuCategoryRequest request);

    @Mapping(source = "menu.id", target = "menuId")
    MenuCategoryResponse toResponse(MenuCategory entity);
}