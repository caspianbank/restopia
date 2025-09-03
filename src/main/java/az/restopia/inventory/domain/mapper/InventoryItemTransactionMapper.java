package az.restopia.inventory.domain.mapper;

import az.restopia.inventory.domain.entity.InventoryItemTransaction;
import az.restopia.inventory.domain.request.InventoryItemTransactionRequest;
import az.restopia.inventory.domain.response.InventoryItemTransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryItemTransactionMapper {

    @Mapping(source = "inventoryItem.id", target = "inventoryItemId")
    @Mapping(source = "store.id", target = "storeId")
    InventoryItemTransactionResponse toResponse(InventoryItemTransaction entity);

    InventoryItemTransaction toEntity(InventoryItemTransactionRequest request);
}
