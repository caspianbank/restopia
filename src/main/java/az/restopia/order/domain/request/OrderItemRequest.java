package az.restopia.order.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemRequest {

    private Long menuItemId;
    private int quantity;
    private BigDecimal totalPrice;
}
