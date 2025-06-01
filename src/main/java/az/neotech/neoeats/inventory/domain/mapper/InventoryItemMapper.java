package az.neotech.neoeats.inventory.domain.mapper;

import az.neotech.neoeats.inventory.domain.entity.InventoryItem;
import az.neotech.neoeats.inventory.domain.request.InventoryItemRequest;
import az.neotech.neoeats.inventory.domain.response.InventoryItemResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InventoryItemMapper {

    @Mapping(target = "business.id", source = "businessId")
    @Mapping(target = "itemCategory.id", source = "itemCategoryId")
    @Mapping(target = "supplier.id", source = "supplierId")
    InventoryItem toEntity(InventoryItemRequest request);

    @Mapping(source = "business.id", target = "businessId")
    @Mapping(source = "itemCategory.id", target = "itemCategoryId")
    @Mapping(source = "supplier.id", target = "supplierId")
    InventoryItemResponse toResponse(InventoryItem item);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "business.id", source = "businessId")
    @Mapping(target = "itemCategory.id", source = "itemCategoryId")
    @Mapping(target = "supplier.id", source = "supplierId")
    void update(@MappingTarget InventoryItem item, InventoryItemRequest request);
}
