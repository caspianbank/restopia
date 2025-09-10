package az.restopia.order.service.impl;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.menu.service.MenuItemService;
import az.restopia.order.domain.entity.Order;
import az.restopia.order.domain.entity.OrderItem;
import az.restopia.order.domain.enums.OrderStatus;
import az.restopia.order.domain.request.OrderItemRequest;
import az.restopia.order.domain.request.OrderRequest;
import az.restopia.order.domain.response.OrderCountResponse;
import az.restopia.order.domain.response.OrderItemResponse;
import az.restopia.order.domain.response.OrderResponse;
import az.restopia.order.repository.OrderRepository;
import az.restopia.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemService menuItemService;

    public Page<OrderResponse> getOrders(String tenantCode, OrderStatus status, Pageable pageable) {
        Page<Order> orders;
        if (status != null) {
            orders = orderRepository.findByTenantCodeAndDeleteStatusAndStatus(
                    tenantCode, DeleteStatus.ACTIVE, status, pageable
            );
        } else {
            orders = orderRepository.findByTenantCodeAndDeleteStatus(
                    tenantCode, DeleteStatus.ACTIVE, pageable
            );
        }
        return orders.map(this::convertToResponse);
    }

    @Transactional
    public OrderResponse createOrder(String tenantCode, OrderRequest request) {
        Order order = new Order();
        order.setTenantCode(tenantCode);
        order.setSource(request.getSource());
        order.setType(request.getType());
        order.setStatus(request.getStatus() != null ? request.getStatus() : OrderStatus.NEW);
        order.setTableNumber(request.getTableNumber());
        order.setCustomerName(request.getCustomerName());
        order.setCustomerNote(request.getCustomerNote());
        order.setDescription(request.getDescription());
        order.setDeleteStatus(DeleteStatus.ACTIVE);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : request.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenuItem(menuItemService.getMenuItemByIdOrThrowNotFound(itemRequest.getMenuItemId()));
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setTotalPrice(itemRequest.getTotalPrice());
            orderItems.add(orderItem);
        }
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        return convertToResponse(savedOrder);
    }

    @Transactional
    public OrderResponse updateOrder(String tenantCode, String id, OrderRequest request) {
        Order order = orderRepository.findByIdAndTenantCodeAndDeleteStatus(
                id, tenantCode, DeleteStatus.ACTIVE
        ).orElseThrow(() -> new RecordNotFoundException("Order not found"));

        order.setSource(request.getSource());
        order.setType(request.getType());
        order.setStatus(request.getStatus());
        order.setTableNumber(request.getTableNumber());
        order.setCustomerName(request.getCustomerName());
        order.setCustomerNote(request.getCustomerNote());
        order.setDescription(request.getDescription());

        order.getItems().clear();
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : request.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenuItem(menuItemService.getMenuItemByIdOrThrowNotFound(itemRequest.getMenuItemId()));
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setTotalPrice(itemRequest.getTotalPrice());
            orderItems.add(orderItem);
        }
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        return convertToResponse(savedOrder);
    }

    @Transactional
    public void cancelOrder(String tenantCode, String id) {
        Order order = orderRepository.findByIdAndTenantCodeAndDeleteStatus(
                id, tenantCode, DeleteStatus.ACTIVE
        ).orElseThrow(() -> new RecordNotFoundException("Order not found"));

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    public OrderResponse getOrderById(String tenantCode, String id) {
        Order order = orderRepository.findByIdAndTenantCodeAndDeleteStatus(
                id, tenantCode, DeleteStatus.ACTIVE
        ).orElseThrow(() -> new RecordNotFoundException("Order not found"));

        return convertToResponse(order);
    }

    public Page<OrderResponse> searchOrders(String tenantCode, String searchTerm, Pageable pageable) {
        Page<Order> orders = orderRepository.searchOrders(
                tenantCode, DeleteStatus.ACTIVE, searchTerm, pageable
        );
        return orders.map(this::convertToResponse);
    }

    public List<OrderCountResponse> getOrderCounts(String tenantCode, String period) {
        List<OrderCountResponse> counts = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        switch (period.toLowerCase()) {
            case "hourly":
                for (int i = 23; i >= 0; i--) {
                    LocalDateTime start = now.minusHours(i).truncatedTo(ChronoUnit.HOURS);
                    LocalDateTime end = start.plusHours(1);
                    long count = orderRepository.countOrdersByDateRange(
                            tenantCode, DeleteStatus.ACTIVE, start, end
                    );
                    counts.add(new OrderCountResponse(start.getHour() + ":00", count));
                }
                break;
            case "daily":
                for (int i = 6; i >= 0; i--) {
                    LocalDateTime start = now.minusDays(i).truncatedTo(ChronoUnit.DAYS);
                    LocalDateTime end = start.plusDays(1);
                    long count = orderRepository.countOrdersByDateRange(
                            tenantCode, DeleteStatus.ACTIVE, start, end
                    );
                    counts.add(new OrderCountResponse(start.toLocalDate().toString(), count
                    ));
                }
                break;
            case "weekly":
                for (int i = 3; i >= 0; i--) {
                    LocalDateTime start = now.minusWeeks(i).truncatedTo(ChronoUnit.DAYS);
                    LocalDateTime end = start.plusWeeks(1);
                    long count = orderRepository.countOrdersByDateRange(
                            tenantCode, DeleteStatus.ACTIVE, start, end
                    );
                    counts.add(new OrderCountResponse("Week " + (4 - i), count));
                }
                break;
        }

        return counts;
    }

    private OrderResponse convertToResponse(Order order) {
        var response = new OrderResponse();
        response.setTenantCode(order.getTenantCode());
        response.setSource(order.getSource());
        response.setType(order.getType());
        response.setStatus(order.getStatus());
        response.setTableNumber(order.getTableNumber());
        response.setCustomerName(order.getCustomerName());
        response.setCustomerNote(order.getCustomerNote());
        response.setDescription(order.getDescription());
        response.setCreatedDateTime(order.getCreatedDateTime());
        response.setModifiedDateTime(order.getModifiedDateTime());
        response.setCreatedBy(order.getCreatedBy());
        response.setLastModifiedBy(order.getModifiedBy());

        List<OrderItemResponse> itemResponses = order.getItems().stream()
                .map(item -> {
                    var itemResponse = new OrderItemResponse();
                    itemResponse.setMenuItemId(item.getMenuItem().getId());
                    itemResponse.setMenuItemName(item.getMenuItem().getName());
                    itemResponse.setQuantity(item.getQuantity());
                    itemResponse.setTotalPrice(item.getTotalPrice());
                    return itemResponse;
                }).toList();

        response.setItems(itemResponses);
        return response;
    }

}
