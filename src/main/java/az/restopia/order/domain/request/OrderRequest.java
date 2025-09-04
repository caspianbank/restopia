package az.restopia.order.domain.request;

import az.restopia.order.domain.enums.OrderSource;
import az.restopia.order.domain.enums.OrderStatus;
import az.restopia.order.domain.enums.OrderType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private String tenantCode;

    @NotNull
    private OrderSource source;

    @NotNull
    private OrderType type;

    private OrderStatus status;

    private String tableNumber;

    private String customerName;

    private String customerNote;

    private String description;

    @Valid
    @NotEmpty
    private List<OrderItemRequest> items;
}
