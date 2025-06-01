package az.neotech.neoeats.inventory.domain.response;

import az.neotech.neoeats.inventory.domain.enums.PurchaseOrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record PurchaseOrderResponse(
        Long id,
        String tenantCode,
        Long supplierId,
        LocalDateTime orderDate,
        LocalDateTime receivedDate,
        PurchaseOrderStatus status,
        List<PurchaseOrderItemResponse> items
) {}
