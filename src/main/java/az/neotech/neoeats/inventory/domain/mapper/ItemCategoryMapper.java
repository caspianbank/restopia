package az.neotech.neoeats.inventory.domain.mapper;

import az.neotech.neoeats.inventory.domain.entity.ItemCategory;
import az.neotech.neoeats.inventory.domain.request.ItemCategoryRequest;
import az.neotech.neoeats.inventory.domain.response.ItemCategoryResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ItemCategoryMapper {

    ItemCategory toEntity(ItemCategoryRequest request);

    @Mapping(source = "parent.id", target = "parentId")
    ItemCategoryResponse toResponse(ItemCategory category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateItemCategoryFromRequest(ItemCategoryRequest request, @MappingTarget ItemCategory category);
}