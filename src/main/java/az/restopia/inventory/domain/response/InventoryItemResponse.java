package az.restopia.inventory.domain.response;

import az.restopia.inventory.domain.enums.ItemUnit;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryItemResponse {

    private Long id;
    private String sku;
    private String name;
    private String description;
    private String barcode;
    private BigDecimal currentQuantity;
    private BigDecimal reservedQuantity;
    private BigDecimal availableQuantity;
    private BigDecimal minimumQuantity;
    private ItemUnit unit;
    private BigDecimal unitCost;
    private BigDecimal totalValue;
    private LocalDate expiryDate;
    private String batchNumber;
    private Integer storageTemperatureMin;
    private Integer storageTemperatureMax;
    private boolean requiresRefrigeration;
    private boolean perishable;
    private boolean trackExpiry;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
