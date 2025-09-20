package az.restopia.menu.domain.enums;

public enum PricingRuleType {
    HAPPY_HOUR,           // Time-based discount
    TIME_BASED_DISCOUNT,  // General time-based pricing
    COMBO_DISCOUNT,       // Bundle discount
    LOYALTY_DISCOUNT,     // Loyalty program discount
    DYNAMIC_INVENTORY,    // Inventory level-based pricing
    SEASONAL_DISCOUNT,    // Date range-based discount
    BULK_DISCOUNT,        // Quantity-based discount
    AUTOMATIC_DISCOUNT    // Auto-applied promotional discount
}