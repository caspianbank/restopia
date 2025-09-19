package az.restopia.inventory.domain.request;

import az.restopia.inventory.domain.enums.WastageStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryWastageRequest {

    @NotNull(message = "Inventory item ID is required")
    private Long inventoryItemId;

    @NotNull(message = "Wasted quantity is required")
    @DecimalMin(value = "0.001", message = "Wasted quantity must be greater than 0")
    private BigDecimal wastedQuantity;

    @DecimalMin(value = "0.0", message = "Unit cost at wastage must be non-negative")
    private BigDecimal unitCostAtWastage;

    @NotBlank(message = "Wastage reason is required")
    @Size(max = 255, message = "Wastage reason must not exceed 255 characters")
    private String reason;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @NotNull(message = "Wastage date is required")
    private LocalDateTime wastageDate;

    @Size(max = 100, message = "Reported by must not exceed 100 characters")
    private String reportedBy;

    private Long reportedById;

    @Size(max = 100, message = "Approved by must not exceed 100 characters")
    private String approvedBy;

    private Long approvedById;

    private LocalDateTime approvedDate;

    private WastageStatus status;

    @Size(max = 200, message = "Location must not exceed 200 characters")
    private String location;
}