package az.restopia.menu.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.restopia.commons.domain.constants.ColumnLengthConstants;
import az.restopia.inventory.domain.entity.InventoryItem;
import az.restopia.menu.domain.enums.MenuItemType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_items")
public class MenuItem extends DetailedAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_category_id", nullable = false)
    private MenuCategory menuCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", nullable = false)
    private MenuItemType itemType;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MenuItemTranslation> translations = new HashSet<>();

    @Column(name = "base_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal basePrice;

    @Column(name = "cost_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal costPrice;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "color_code")
    private String color;

    @Column(name = "position")
    private Integer position;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "preparation_time")
    private String preparationTime; // 45s -> 45 seconds, 10m - 10 minutes (must be translated)

    /**
     * Points awarded to customers when they order this item
     */
    @Column(name = "loyalty_points", nullable = false)
    private Integer loyaltyPoints = 0;

    // ---------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_item_id")
    private InventoryItem inventoryItem;

    @Column(name = "inventory_item_quantity", precision = 10, scale = 3, nullable = false)
    private BigDecimal inventoryItemQuantity;

    @Column(name = "additional_price", precision = 10, scale = 2)
    private BigDecimal additionalPrice = BigDecimal.ZERO;

    @Column(name = "waste_quantity", precision = 10, scale = 3)
    private BigDecimal wasteQuantity = BigDecimal.ZERO; // Fixed amount of waste

    @Column(name = "waste_reason", length = 200)
    private String wasteReason;

    @Column(name = "is_replaceable", nullable = false)
    private boolean replaceable = false;

    // Replacement options. e.g. Coca-Cola can be replaced with (Pepsi, Sprite)
    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MenuItem> availableReplacements = new HashSet<>();

    // Recipe for this menu item
    @OneToOne(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private MenuItemRecipe recipe;

    // Choice groups for business lunch category-based selections
    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MenuItemChoiceGroup> choiceGroups = new HashSet<>();

    // --------------

    // Business lunch category rules
    @OneToMany(mappedBy = "businessLunchItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BusinessLunchCategoryRule> categoryRules = new HashSet<>();

    // Optional add-ons for business lunches
    @OneToMany(mappedBy = "businessLunchItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BusinessLunchAddon> addons = new HashSet<>();

    // Pricing rules and discounts
    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MenuItemPricingRule> pricingRules = new HashSet<>();

    // Promo code eligibility
    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MenuItemPromoEligibility> promoEligibilities = new HashSet<>();
}
