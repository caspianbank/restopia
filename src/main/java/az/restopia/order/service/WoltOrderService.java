package az.restopia.order.service;

import az.restopia.order.domain.response.BusinessWoltVenueResponse;

public interface WoltOrderService {

    void acceptOrder(String orderId, BusinessWoltVenueResponse businessWoltVenue);

}
