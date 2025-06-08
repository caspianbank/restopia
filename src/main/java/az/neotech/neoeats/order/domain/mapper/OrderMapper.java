package az.neotech.neoeats.order.domain.mapper;

import az.neotech.neoeats.order.domain.entity.Order;
import az.neotech.neoeats.order.domain.entity.OrderItem;
import az.neotech.neoeats.order.domain.request.OrderRequest;
import az.neotech.neoeats.order.domain.response.OrderItemResponse;
import az.neotech.neoeats.order.domain.response.OrderResponse;
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
}
