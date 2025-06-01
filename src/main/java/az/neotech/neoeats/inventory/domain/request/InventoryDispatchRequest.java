package az.neotech.neoeats.inventory.domain.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class InventoryDispatchRequest {

    @NotBlank
    private String tenantCode;

    @NotNull
    private Long toStoreId;

    @NotNull
    private LocalDateTime dispatchDate;

    private String note;

    @NotNull
    private List<InventoryDispatchItemRequest> items;

    @Getter
    @Setter
    public static class InventoryDispatchItemRequest {

        @NotNull
        private Long inventoryItemId;

        @NotNull
        private BigDecimal quantity;
    }
}