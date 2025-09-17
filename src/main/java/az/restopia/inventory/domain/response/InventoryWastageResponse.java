package az.restopia.inventory.domain.response;

import az.restopia.inventory.domain.enums.WastageStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryWastageResponse {

    private Long id;
    private String wastageReference;
    private Long inventoryItemId;
    private String inventoryItemSku;
    private String inventoryItemName;
    private BigDecimal wastedQuantity;
    private BigDecimal unitCostAtWastage;
    private BigDecimal totalLossAmount;
    private String reason;
    private String description;
    private LocalDateTime wastageDate;
    private String reportedBy;
    private Long reportedById;
    private String approvedBy;
    private Long approvedById;
    private LocalDateTime approvedDate;
    private WastageStatus status;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}