package az.restopia.inventory.domain.mapper;

import az.restopia.inventory.domain.entity.InventoryItem;
import az.restopia.inventory.domain.request.InventoryItemRequest;
import az.restopia.inventory.domain.response.InventoryItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantCode", ignore = true)
    @Mapping(target = "inventory", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    InventoryItem toEntity(InventoryItemRequest request);

    @Mapping(target = "availableQuantity", expression = "java(entity.getAvailableQuantity())")
    @Mapping(target = "totalValue", expression = "java(entity.getTotalValue())")
    InventoryItemResponse toResponse(InventoryItem entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantCode", ignore = true)
    @Mapping(target = "inventory", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(InventoryItemRequest request, @MappingTarget InventoryItem entity);
}