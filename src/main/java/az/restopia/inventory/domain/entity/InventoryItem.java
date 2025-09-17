package az.restopia.inventory.domain.entity;

import az.neotech.commons.audit.DateAudit;
import az.restopia.commons.domain.constants.ColumnLengthConstants;
import az.restopia.inventory.domain.enums.ItemUnit;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tenant_inventory_items")
public class InventoryItem extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    /**
     * Stock Keeping Unit (SKU) - Internal unique identifier for inventory tracking.
     * Business-specific alphanumeric code (e.g., CHK-BRST-1KG, KIT001).
     * Must be unique within tenant scope. Supports international characters.
     */
    @Column(name = "sku", nullable = false, length = 100)
    private String sku; // todo: when adding a new sku, check tenant code with sku because it has to be unique

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "barcode", length = 100)
    private String barcode;

    /**
     * Current stock quantity in the inventory
     */
    @Column(name = "current_quantity", nullable = false, precision = 10, scale = 3)
    private BigDecimal currentQuantity = BigDecimal.ZERO;

    /**
     * Reserved quantity (allocated but not yet consumed/shipped)
     */
    @Column(name = "reserved_quantity", nullable = false, precision = 10, scale = 3)
    private BigDecimal reservedQuantity = BigDecimal.ZERO;

    /**
     * Minimum stock level - triggers low stock alerts
     */
    @Column(name = "minimum_quantity", precision = 10, scale = 3)
    private BigDecimal minimumQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_unit", nullable = false)
    private ItemUnit unit;

    /**
     * Cost per unit (for inventory valuation)
     */
    @Column(name = "unit_cost", precision = 10, scale = 2)
    private BigDecimal unitCost;

    /**
     * Expiry tracking for perishable items
     */
    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "batch_number", length = 100)
    private String batchNumber;

    /**
     * Storage conditions
     */
    @Column(name = "storage_temperature_min")
    private Integer storageTemperatureMin; // Celsius

    @Column(name = "storage_temperature_max")
    private Integer storageTemperatureMax; // Celsius

    @Column(name = "requires_refrigeration", nullable = false)
    private boolean requiresRefrigeration = false;

    @Column(name = "is_perishable", nullable = false)
    private boolean perishable = false;

    @Column(name = "track_expiry", nullable = false)
    private boolean trackExpiry = false;

    /**
     * Available quantity = current_quantity - reserved_quantity
     */
    public BigDecimal getAvailableQuantity() {
        return currentQuantity.subtract(reservedQuantity);
    }

    /**
     * Total inventory value = current_quantity * unit_cost
     */
    public BigDecimal getTotalValue() {
        if (unitCost == null || currentQuantity == null) {
            return BigDecimal.ZERO;
        }
        return currentQuantity.multiply(unitCost);
    }


}
