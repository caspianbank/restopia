package az.restopia.order.client;

import az.restopia.order.config.WoltFeignConfig;
import az.restopia.order.domain.response.WoltOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-wolt-food", url = "${feign.client.wolt-food}", configuration = WoltFeignConfig.class)
public interface WoltClient {

    @GetMapping("/v2/orders/{orderId}")
    WoltOrderResponse getOrder(@PathVariable("orderId") String orderId);
}
