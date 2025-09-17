package az.restopia.inventory.domain.entity;

import az.neotech.commons.audit.DateAudit;
import az.restopia.commons.domain.constants.ColumnLengthConstants;
import az.restopia.inventory.domain.enums.WastageStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tenant_inventory_wastages")
public class InventoryWastage extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private InventoryItem inventoryItem;

    /**
     * Reference number for wastage tracking (e.g., WASTE-2024-001)
     */
    @Column(name = "wastage_reference", nullable = false, length = 50)
    private String wastageReference;

    /**
     * Quantity wasted
     */
    @Column(name = "wasted_quantity", nullable = false, precision = 10, scale = 3)
    private BigDecimal wastedQuantity;

    /**
     * Unit cost at the time of wastage (for financial impact calculation)
     */
    @Column(name = "unit_cost_at_wastage", precision = 10, scale = 2)
    private BigDecimal unitCostAtWastage;

    /**
     * Total financial loss = wasted_quantity * unit_cost_at_wastage
     */
    @Column(name = "total_loss_amount", precision = 12, scale = 2)
    private BigDecimal totalLossAmount;

    @Column(name = "wastage_reason", nullable = false)
    private String reason; // this can come from enum, but it has to be populated by client

    /**
     * Detailed description of the wastage incident
     */
    @Column(name = "description", length = 1000)
    private String description;

    /**
     * Date and time when wastage occurred
     */
    @Column(name = "wastage_date", nullable = false)
    private LocalDateTime wastageDate;

    /**
     * Employee who reported the wastage
     */
    @Column(name = "reported_by", length = 100)
    private String reportedBy;

    /**
     * Employee ID who reported the wastage
     */
    @Column(name = "reported_by_id")
    private Long reportedById;

    /**
     * Manager who approved/verified the wastage
     */
    @Column(name = "approved_by", length = 100)
    private String approvedBy;

    /**
     * Manager ID who approved the wastage
     */
    @Column(name = "approved_by_id")
    private Long approvedById;

    /**
     * Date when wastage was approved
     */
    @Column(name = "approved_date")
    private LocalDateTime approvedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private WastageStatus status;

    /**
     * Location where wastage occurred
     */
    @Column(name = "location", length = 200)
    private String location;
}