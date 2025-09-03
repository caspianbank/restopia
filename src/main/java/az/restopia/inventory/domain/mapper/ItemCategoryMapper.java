package az.restopia.inventory.domain.mapper;

import az.restopia.inventory.domain.entity.ItemCategory;
import az.restopia.inventory.domain.request.ItemCategoryRequest;
import az.restopia.inventory.domain.response.ItemCategoryResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ItemCategoryMapper {

    ItemCategory toEntity(ItemCategoryRequest request);

    @Mapping(source = "parent.id", target = "parentId")
    ItemCategoryResponse toResponse(ItemCategory category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateItemCategoryFromRequest(ItemCategoryRequest request, @MappingTarget ItemCategory category);
}