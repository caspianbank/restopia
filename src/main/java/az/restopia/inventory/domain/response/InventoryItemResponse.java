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
    private String taxCategoryCode;
    private BigDecimal vatRate;
    private BigDecimal additionalTaxRate;
    private boolean taxExempt;
    private String taxExemptionCode;

    /**
     * VAT amount per unit based on unit cost and VAT rate
     */
    private BigDecimal vatAmountPerUnit;

    /**
     * Total tax amount per unit (VAT + additional taxes)
     */
    private BigDecimal totalTaxAmountPerUnit;

    /**
     * Unit cost excluding all taxes (base price)
     */
    private BigDecimal unitCostExcludingTax;

    /**
     * Unit cost including all taxes (final price)
     */
    private BigDecimal unitCostIncludingTax;

    /**
     * Total tax value for current inventory (totalTaxAmountPerUnit * currentQuantity)
     */
    private BigDecimal totalTaxValue;

    /**
     * Effective tax rate (combined VAT + additional taxes as percentage)
     */
    private BigDecimal effectiveTaxRate;
    private Integer storageTemperatureMin;
    private Integer storageTemperatureMax;
    private boolean requiresRefrigeration;
    private boolean perishable;
    private boolean trackExpiry;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}