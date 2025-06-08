package az.neotech.neoeats.order.domain.request;

import az.neotech.neoeats.order.domain.enums.OrderSource;
import az.neotech.neoeats.order.domain.enums.OrderStatus;
import az.neotech.neoeats.order.domain.enums.OrderType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private String tenantCode;
    private OrderSource source;
    private OrderType type;
    private OrderStatus status;
    private String tableNumber;
    private String customerName;
    private String customerNote;
    private String description;
    private List<OrderItemRequest> items;
}
