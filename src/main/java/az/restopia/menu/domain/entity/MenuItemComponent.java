package az.restopia.menu.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.restopia.inventory.domain.entity.InventoryItem;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_item_components")
public class MenuItemComponent extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_menu_item_id", nullable = false)
    private MenuItem parentMenuItem;

    // For combo items - reference to component menu item
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_menu_item_id")
    private MenuItem componentMenuItem;

    // For inventory tracking - reference to inventory item  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_item_id")
    private InventoryItem inventoryItem;

    @Column(name = "quantity", precision = 10, scale = 3, nullable = false)
    private BigDecimal quantity;

    @Column(name = "is_optional", nullable = false)
    private boolean optional = false;

    @Column(name = "additional_price", precision = 10, scale = 2)
    private BigDecimal additionalPrice = BigDecimal.ZERO;

    @Column(name = "is_critical", nullable = false)
    private boolean critical = true;

    @Column(name = "waste_percentage", precision = 5, scale = 2)
    private BigDecimal wastePercentage = BigDecimal.ZERO; // % of ingredient that becomes waste

    @Column(name = "waste_quantity", precision = 10, scale = 3)
    private BigDecimal wasteQuantity = BigDecimal.ZERO; // Fixed amount of waste

    @Column(name = "waste_reason", length = 200)
    private String wasteReason; // e.g., "peeling", "trimming", "unusable portion"

    @Column(name = "yield_percentage", precision = 5, scale = 2)
    private BigDecimal yieldPercentage = BigDecimal.valueOf(100); // Usable portion %

    @Column(name = "is_replaceable", nullable = false)
    private boolean replaceable = false;

    @Column(name = "replacement_group_name", length = 100)
    private String replacementGroupName; // e.g., "Drinks", "Side Dishes"

    @Column(length = 200)
    private String notes;

    // Validation: Either componentMenuItem OR inventoryItem must be set, not both
    @PrePersist
    @PreUpdate
    private void validateComponent() {
        if ((componentMenuItem == null && inventoryItem == null) || 
            (componentMenuItem != null && inventoryItem != null)) {
            throw new IllegalStateException("Either componentMenuItem or inventoryItem must be set, but not both");
        }
    }
}