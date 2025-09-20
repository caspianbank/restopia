package az.restopia.menu.domain.enums;

public enum MenuItemType {
    REGULAR,        // Regular standalone item with direct inventory connections
    COMBO,          // Fixed combo item composed of other menu items
    BUSINESS_LUNCH  // Business lunch with category-based choices and rules
}
