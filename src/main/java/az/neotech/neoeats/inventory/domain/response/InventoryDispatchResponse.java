package az.neotech.neoeats.inventory.domain.response;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class InventoryDispatchResponse {
    private Long id;
    private String tenantCode;
    private Long toStoreId;
    private LocalDateTime dispatchDate;
    private String note;
    private List<InventoryDispatchItemResponse> items;

    @Getter
    @Setter
    public static class InventoryDispatchItemResponse {
        private Long id;
        private Long inventoryItemId;
        private BigDecimal quantity;
    }
}