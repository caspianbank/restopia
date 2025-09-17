package az.restopia.inventory.domain.mapper;

import az.restopia.inventory.domain.entity.InventoryWastage;
import az.restopia.inventory.domain.request.InventoryWastageRequest;
import az.restopia.inventory.domain.response.InventoryWastageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InventoryWastageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantCode", ignore = true)
    @Mapping(target = "inventoryItem", ignore = true)
    @Mapping(target = "wastageReference", ignore = true)
    @Mapping(target = "totalLossAmount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    InventoryWastage toEntity(InventoryWastageRequest request);

    @Mapping(target = "inventoryItemId", source = "inventoryItem.id")
    @Mapping(target = "inventoryItemSku", source = "inventoryItem.sku")
    @Mapping(target = "inventoryItemName", source = "inventoryItem.name")
    InventoryWastageResponse toResponse(InventoryWastage entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tenantCode", ignore = true)
    @Mapping(target = "inventoryItem", ignore = true)
    @Mapping(target = "wastageReference", ignore = true)
    @Mapping(target = "totalLossAmount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(InventoryWastageRequest request, @MappingTarget InventoryWastage entity);
}