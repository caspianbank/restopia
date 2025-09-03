package az.restopia.inventory.domain.enums;

public enum ItemUnit {
    UNKNOWN,

    // Count-based units
    PCS,        // Pieces
    BOX,        // Box (multiple items inside)
    PACK,       // Pack
    BOTTLE,     // Bottle
    CAN,        // Can
    BAG,        // Bag
    TIN,        // Tin

    // Weight-based units
    G,          // Gram
    KG,         // Kilogram
    MG,         // Milligram
    LB,         // Pound
    OZ,         // Ounce

    // Volume-based units
    ML,         // Milliliter
    L,          // Liter
    GAL,        // Gallon
    FL_OZ,      // Fluid ounce

    // Other
    SLICE,      // Slice (e.g. cheese, bread)
    DROP,       // Drop (e.g. food coloring)
    PORTION,    // Custom portion
    ROLL,       // Roll (e.g. napkins, sushi)
    UNIT        // Generic unit (fallback)
}
