package az.restopia.inventory.domain.mapper;

import az.restopia.inventory.domain.entity.InventoryItemCategory;
import az.restopia.inventory.domain.request.InventoryItemCategoryRequest;
import az.restopia.inventory.domain.response.InventoryItemCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InventoryItemCategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(source = "parentId", target = "parent.id")
    InventoryItemCategory toEntity(InventoryItemCategoryRequest request);

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "parent.name", target = "parentName")
    InventoryItemCategoryResponse toResponse(InventoryItemCategory entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(source = "parentId", target = "parent.id")
    void updateEntityFromRequest(InventoryItemCategoryRequest request, @MappingTarget InventoryItemCategory entity);
}