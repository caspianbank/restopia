package az.restopia.inventory.domain.request;

import az.restopia.inventory.domain.enums.PurchaseOrderStatus;

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