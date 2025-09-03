package az.restopia.inventory.domain.response;

import az.restopia.inventory.domain.enums.ItemUnit;

import java.math.BigDecimal;

public record InventoryItemResponse(
        Long id,
        String tenantCode,
        String name,
        String description,
        BigDecimal quantityOnHand,
        ItemUnit unit,
        BigDecimal costPrice,
        Long supplierId,
        Long itemCategoryId,
        Long businessId
) {}
