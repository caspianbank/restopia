package az.restopia.order.domain.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemRequest {

    @NotNull
    private Long menuItemId;

    @Positive
    private int quantity;

    private BigDecimal totalPrice;
}
