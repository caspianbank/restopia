package az.neotech.neoeats.inventory.domain.request;

import az.neotech.neoeats.inventory.domain.enums.ItemUnit;

import java.math.BigDecimal;

public record InventoryItemRequest(
        String tenantCode,
        Long businessId,
        Long itemCategoryId,
        String name,
        String description,
        BigDecimal quantityOnHand,
        ItemUnit unit,
        BigDecimal costPrice,
        Long supplierId
) {}
