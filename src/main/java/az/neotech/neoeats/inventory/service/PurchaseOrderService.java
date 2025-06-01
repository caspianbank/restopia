package az.neotech.neoeats.inventory.service;

import az.neotech.neoeats.inventory.domain.request.PurchaseOrderRequest;
import az.neotech.neoeats.inventory.domain.response.PurchaseOrderResponse;

import java.util.List;

public interface PurchaseOrderService {

    List<PurchaseOrderResponse> getAllPurchaseOrders();

    PurchaseOrderResponse getPurchaseOrderById(Long id);

    PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request);

    PurchaseOrderResponse updatePurchaseOrder(Long id, PurchaseOrderRequest request);

    void deletePurchaseOrder(Long id);
}