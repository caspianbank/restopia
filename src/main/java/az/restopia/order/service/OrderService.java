package az.restopia.order.service;

import az.restopia.order.domain.request.OrderRequest;
import az.restopia.order.domain.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);

    OrderResponse updateOrder(String orderId, OrderRequest request);

    void deleteOrder(String orderId);

    List<OrderResponse> findAllOrders(String tenantCode);
}
