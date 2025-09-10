package az.restopia.order.controller;

import az.restopia.order.domain.request.WoltOrderWebhookRequest;
import az.restopia.order.domain.response.BusinessWoltVenueResponse;
import az.restopia.order.service.BusinessWoltVenueService;
import az.restopia.order.service.WoltOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webhook/wolt")
@RequiredArgsConstructor
public class WoltOrderWebhookController {

    private final BusinessWoltVenueService businessWoltVenueService;
    private final WoltOrderService woltOrderService;

    @PostMapping("/order")
    public void receiveOrderNotification(@RequestBody WoltOrderWebhookRequest request) {
        BusinessWoltVenueResponse response = businessWoltVenueService.getBusinessWoltVenueOrThrow(request.venueId());
        woltOrderService.acceptOrder(request.orderId(), response);
    }

}
