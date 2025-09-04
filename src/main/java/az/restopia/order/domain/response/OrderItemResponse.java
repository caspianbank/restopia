package az.restopia.order.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponse {
    private Long menuItemId;
    private String menuItemName;
    private int quantity;
    private BigDecimal totalPrice;
}
