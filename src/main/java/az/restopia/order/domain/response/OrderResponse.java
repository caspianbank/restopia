package az.restopia.order.domain.response;

import az.restopia.order.domain.enums.OrderSource;
import az.restopia.order.domain.enums.OrderStatus;
import az.restopia.order.domain.enums.OrderType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private String tenantCode;
    private OrderSource source;
    private OrderType type;
    private OrderStatus status;
    private String tableNumber;
    private String customerName;
    private String customerNote;
    private List<OrderItemResponse> items;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
}