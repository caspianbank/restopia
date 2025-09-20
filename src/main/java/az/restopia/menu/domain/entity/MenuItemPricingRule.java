package az.restopia.menu.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.restopia.menu.domain.enums.PricingRuleType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_item_pricing_rules")
public class MenuItemPricingRule extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @Column(name = "rule_name", nullable = false, length = 100)
    private String ruleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "rule_type", nullable = false)
    private PricingRuleType ruleType;

    @Column(name = "discount_percentage", precision = 5, scale = 2)
    private BigDecimal discountPercentage;

    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "new_price", precision = 10, scale = 2)
    private BigDecimal newPrice;

    // Time-based rules
    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "valid_days", length = 20)
    private String validDays; // JSON: ["MON","TUE","WED"]

    // Date-based rules
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    // Inventory-based dynamic pricing
    @Column(name = "inventory_threshold_low")
    private BigDecimal inventoryThresholdLow;

    @Column(name = "inventory_threshold_high")
    private BigDecimal inventoryThresholdHigh;

    @Column(name = "low_inventory_price_multiplier", precision = 5, scale = 2)
    private BigDecimal lowInventoryPriceMultiplier; // e.g., 1.2 for 20% increase

    @Column(name = "high_inventory_price_multiplier", precision = 5, scale = 2)
    private BigDecimal highInventoryPriceMultiplier; // e.g., 0.9 for 10% decrease

    @Column(name = "priority", nullable = false)
    private Integer priority = 0; // Higher number = higher priority

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @Column(name = "max_usage_count")
    private Integer maxUsageCount;

    @Column(name = "current_usage_count", nullable = false)
    private Integer currentUsageCount = 0;
}