package az.neotech.neoeats.inventory.domain.request;

import az.neotech.neoeats.inventory.domain.enums.PurchaseOrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record PurchaseOrderRequest(
        String tenantCode,
        Long supplierId,
        LocalDateTime orderDate,
        LocalDateTime receivedDate,
        PurchaseOrderStatus status,
        List<PurchaseOrderItemRequest> items
) {}