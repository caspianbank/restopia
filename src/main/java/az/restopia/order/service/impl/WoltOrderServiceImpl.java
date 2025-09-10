package az.restopia.order.service.impl;

import az.restopia.order.client.WoltClient;
import az.restopia.order.domain.entity.Order;
import az.restopia.order.domain.mapper.OrderMapper;
import az.restopia.order.domain.response.BusinessWoltVenueResponse;
import az.restopia.order.domain.response.WoltOrderResponse;
import az.restopia.order.service.OrderService;
import az.restopia.order.service.WoltOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class WoltOrderServiceImpl implements WoltOrderService {

    private final WoltClient woltClient;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Override
    public void acceptOrder(String orderId, BusinessWoltVenueResponse businessWoltVenue) {
        WoltOrderResponse orderResponse = woltClient.getOrder(orderId);
        Order order = orderMapper.mapToOrder(orderResponse, businessWoltVenue);
        orderService.createOrder(order);
    }
}
