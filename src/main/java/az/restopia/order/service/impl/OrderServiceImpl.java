package az.restopia.order.service.impl;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.menu.domain.entity.MenuItem;
import az.restopia.menu.repository.MenuItemRepository;
import az.restopia.order.domain.entity.Order;
import az.restopia.order.domain.entity.OrderItem;
import az.restopia.order.domain.mapper.OrderMapper;
import az.restopia.order.domain.request.OrderItemRequest;
import az.restopia.order.domain.request.OrderRequest;
import az.restopia.order.domain.response.OrderResponse;
import az.restopia.order.repository.OrderRepository;
import az.restopia.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    // todo: consider logging, using only service interfaces between packages

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> findAllOrders(String tenantCode) {
        List<Order> orders = orderRepository.findByTenantCode(tenantCode);
        return orders.stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Order order = orderMapper.toEntity(request);
        orderRepository.save(order);
        log.debug("Order created: {}", order);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse updateOrder(String orderId, OrderRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RecordNotFoundException("Order not found"));

        // Clear previous items
        order.getItems().clear();

        // Update order fields
        order.setSource(request.getSource());
        order.setType(request.getType());
        order.setStatus(request.getStatus());
        order.setCustomerName(request.getCustomerName());
        order.setCustomerNote(request.getCustomerNote());
        order.setTableNumber(request.getTableNumber());

        List<OrderItem> updatedItems = buildOrderItems(request.getItems(), order);
        order.setItems(updatedItems);

        return orderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RecordNotFoundException("Order not found"));
        order.setDeleteStatus(DeleteStatus.DELETED);
        orderRepository.save(order);
    }

    private List<OrderItem> buildOrderItems(List<OrderItemRequest> itemRequests, Order order) {
        return itemRequests.stream().map(itemRequest -> {
            MenuItem menuItem = menuItemRepository.findById(itemRequest.getMenuItemId())
                    .orElseThrow(() ->
                            new RecordNotFoundException("Menu item not found: " + itemRequest.getMenuItemId()));

            OrderItem item = new OrderItem();
            item.setMenuItem(menuItem);
            item.setQuantity(itemRequest.getQuantity());
            item.setTotalPrice(menuItem.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
            item.setOrder(order);
            return item;
        }).toList();
    }
}
