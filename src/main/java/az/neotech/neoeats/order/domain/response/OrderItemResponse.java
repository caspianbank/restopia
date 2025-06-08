package az.neotech.neoeats.order.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponse {
    private Long menuItemId;
    private String itemName;
    private int quantity;
    private BigDecimal totalPrice;
}
