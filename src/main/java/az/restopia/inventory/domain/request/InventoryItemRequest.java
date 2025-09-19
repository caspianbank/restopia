package az.restopia.inventory.domain.request;

import az.restopia.inventory.domain.enums.ItemUnit;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class InventoryItemRequest {

    @NotBlank(message = "SKU is required")
    @Size(max = 100, message = "SKU must not exceed 100 characters")
    private String sku;

    @NotBlank(message = "Name is required")
    @Size(max = 200, message = "Name must not exceed 200 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @Size(max = 100, message = "Barcode must not exceed 100 characters")
    private String barcode;

    @NotNull(message = "Current quantity is required")
    @DecimalMin(value = "0.0", message = "Current quantity must be non-negative")
    private BigDecimal currentQuantity;

    @DecimalMin(value = "0.0", message = "Reserved quantity must be non-negative")
    private BigDecimal reservedQuantity = BigDecimal.ZERO;

    @DecimalMin(value = "0.0", message = "Minimum quantity must be non-negative")
    private BigDecimal minimumQuantity;

    @NotNull(message = "Unit is required")
    private ItemUnit unit;

    @DecimalMin(value = "0.0", message = "Unit cost must be non-negative")
    private BigDecimal unitCost;

    private LocalDate expiryDate;

    @Size(max = 100, message = "Batch number must not exceed 100 characters")
    private String batchNumber;

    @Size(max = 50, message = "Tax category code must not exceed 50 characters")
    private String taxCategoryCode;

    @DecimalMin(value = "0.0", message = "VAT rate must be non-negative")
    @DecimalMax(value = "100.0", message = "VAT rate must not exceed 100%")
    private BigDecimal vatRate = BigDecimal.ZERO;

    @DecimalMin(value = "0.0", message = "Additional tax rate must be non-negative")
    @DecimalMax(value = "100.0", message = "Additional tax rate must not exceed 100%")
    private BigDecimal additionalTaxRate = BigDecimal.ZERO;

    private boolean taxExempt = false;

    @Size(max = 50, message = "Tax exemption code must not exceed 50 characters")
    private String taxExemptionCode;

    private Integer storageTemperatureMin;

    private Integer storageTemperatureMax;

    private boolean requiresRefrigeration = false;

    private boolean perishable = false;

    private boolean trackExpiry = false;
}