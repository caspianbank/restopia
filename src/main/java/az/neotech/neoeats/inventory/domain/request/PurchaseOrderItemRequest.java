package az.neotech.neoeats.inventory.domain.request;

import java.math.BigDecimal;

public record PurchaseOrderItemRequest(Long inventoryItemId, BigDecimal quantity, BigDecimal unitCost) {

}