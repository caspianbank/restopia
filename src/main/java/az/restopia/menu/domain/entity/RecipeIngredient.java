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
@Table(name = "recipe_ingredients")
public class RecipeIngredient extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private MenuItemRecipe recipe;

    @Column(name = "ingredient_name", nullable = false, length = 200)
    private String ingredientName;

    @Column(name = "quantity", nullable = false, length = 50)
    private String quantity; // "2 cups", "1 tablespoon", "500g", etc.

    @Column(name = "preparation_note", length = 200)
    private String preparationNote; // "chopped", "diced", "at room temperature"

    @Column(name = "is_optional", nullable = false)
    private boolean optional = false;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder = 0;

    @Column(name = "ingredient_group", length = 100)
    private String ingredientGroup; // "For marinade", "For garnish", etc.

    // Optional link to inventory item for cost calculation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_item_id")
    private InventoryItem inventoryItem;

    @Column(name = "waste_percentage", precision = 5, scale = 2)
    private BigDecimal wastePercentage = BigDecimal.ZERO;

    @Column(name = "waste_reason", length = 200)
    private String wasteReason;

    @Column(name = "yield_percentage", precision = 5, scale = 2)
    private BigDecimal yieldPercentage = BigDecimal.valueOf(100);
}