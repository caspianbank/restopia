package az.restopia.order.domain.mapper;

import az.restopia.order.domain.entity.Order;
import az.restopia.order.domain.entity.OrderItem;
import az.restopia.order.domain.request.OrderRequest;
import az.restopia.order.domain.response.BusinessWoltVenueResponse;
import az.restopia.order.domain.response.OrderItemResponse;
import az.restopia.order.domain.response.OrderResponse;
import az.restopia.order.domain.response.WoltOrderResponse;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "items", source = "items")
    OrderResponse toResponse(Order order);

    Order toEntity(OrderRequest orderRequest);

    List<OrderResponse> toResponseList(List<Order> orders);

    @Mapping(target = "itemName", source = "menuItem.name")
    @Mapping(target = "menuItemId", source = "menuItem.id")
    OrderItemResponse toOrderItemResponse(OrderItem item);

    Order mapToOrder(WoltOrderResponse order, BusinessWoltVenueResponse venue);
}
