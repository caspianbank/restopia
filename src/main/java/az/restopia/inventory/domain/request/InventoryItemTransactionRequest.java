package az.restopia.inventory.domain.request;

import az.restopia.inventory.domain.enums.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryItemTransactionRequest {

    @NotBlank
    private String tenantCode;

    @NotNull
    private Long inventoryItemId;

    @NotNull
    private TransactionType transactionType;

    @NotNull
    @DecimalMin("0.001")
    private BigDecimal quantity;

    private String note;

    private LocalDateTime transactionDateTime;

    private Long storeId;
}
