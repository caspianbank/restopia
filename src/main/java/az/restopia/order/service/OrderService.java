package az.restopia.order.service;

import az.restopia.order.domain.entity.Order;
import az.restopia.order.domain.enums.OrderStatus;
import az.restopia.order.domain.request.OrderRequest;
import az.restopia.order.domain.response.OrderCountResponse;
import az.restopia.order.domain.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Page<OrderResponse> getOrders(String tenantCode, OrderStatus status, Pageable pageable);

    OrderResponse createOrder(String tenantCode, OrderRequest request);

    void createOrder(Order order);

    OrderResponse updateOrder(String tenantCode, String id, OrderRequest request);

    void cancelOrder(String tenantCode, String id);

    OrderResponse getOrderById(String tenantCode, String id);

    Page<OrderResponse> searchOrders(String tenantCode, String searchTerm, Pageable pageable);

    List<OrderCountResponse> getOrderCounts(String tenantCode, String period);

}
