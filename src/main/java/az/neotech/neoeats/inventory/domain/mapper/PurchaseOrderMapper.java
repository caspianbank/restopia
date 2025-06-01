package az.neotech.neoeats.inventory.domain.mapper;

import az.neotech.neoeats.inventory.domain.entity.PurchaseOrder;
import az.neotech.neoeats.inventory.domain.entity.PurchaseOrderItem;
import az.neotech.neoeats.inventory.domain.request.PurchaseOrderRequest;
import az.neotech.neoeats.inventory.domain.response.PurchaseOrderItemResponse;
import az.neotech.neoeats.inventory.domain.response.PurchaseOrderResponse;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {
    PurchaseOrder toEntity(PurchaseOrderRequest request);

    @Mapping(source = "inventoryItem.id", target = "inventoryItemId")
    PurchaseOrderItemResponse toItemResponse(PurchaseOrderItem item);

    List<PurchaseOrderItemResponse> toItemResponseList(Set<PurchaseOrderItem> items);

    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "items", target = "items")
    PurchaseOrderResponse toResponse(PurchaseOrder order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "items", ignore = true)
    void updateEntity(@MappingTarget PurchaseOrder entity, PurchaseOrderRequest request);


}
