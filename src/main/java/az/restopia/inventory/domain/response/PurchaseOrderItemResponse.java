package az.restopia.inventory.domain.response;

import java.math.BigDecimal;

public record PurchaseOrderItemResponse(Long id, Long inventoryItemId, BigDecimal quantity, BigDecimal unitCost) {
}

