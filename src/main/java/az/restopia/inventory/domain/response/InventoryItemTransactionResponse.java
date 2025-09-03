package az.restopia.inventory.domain.response;

import az.restopia.inventory.domain.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryItemTransactionResponse {
    private Long id;
    private String tenantCode;
    private Long inventoryItemId;
    private TransactionType transactionType;
    private BigDecimal quantity;
    private String note;
    private LocalDateTime transactionDateTime;
    private Long storeId;
}
