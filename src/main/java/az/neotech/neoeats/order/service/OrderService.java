package az.neotech.neoeats.order.service;

import az.neotech.neoeats.order.domain.request.OrderRequest;
import az.neotech.neoeats.order.domain.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);

    OrderResponse updateOrder(String orderId, OrderRequest request);

    void deleteOrder(String orderId);

    List<OrderResponse> findAllOrders(String tenantCode);
}
