package az.neotech.neoeats.inventory.domain.mapper;

import az.neotech.neoeats.inventory.domain.entity.InventoryItemTransaction;
import az.neotech.neoeats.inventory.domain.request.InventoryItemTransactionRequest;
import az.neotech.neoeats.inventory.domain.response.InventoryItemTransactionResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InventoryItemTransactionMapper {

    @Mapping(source = "inventoryItem.id", target = "inventoryItemId")
    @Mapping(source = "store.id", target = "storeId")
    InventoryItemTransactionResponse toResponse(InventoryItemTransaction entity);

    InventoryItemTransaction toEntity(InventoryItemTransactionRequest request);
}
