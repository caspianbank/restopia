package az.restopia.inventory.domain.enums;

import lombok.Getter;

@Getter
public enum WastageReason {
    EXPIRY("Expired items"),
    DAMAGE("Physical damage"),
    CONTAMINATION("Contaminated items"),
    SPOILAGE("Spoiled/rotten items"),
    EQUIPMENT_FAILURE("Equipment malfunction"),
    POWER_OUTAGE("Power outage affecting refrigeration"),
    HUMAN_ERROR("Human error/mishandling"),
    TEMPERATURE_ABUSE("Temperature control failure"),
    PEST_INFESTATION("Pest or rodent damage"),
    SPILLAGE("Accidental spillage"),
    QUALITY_DEFECT("Quality defects"),
    OVER_PRODUCTION("Over production waste"),
    THEFT("Theft or unauthorized consumption"),
    NATURAL_DISASTER("Natural disaster damage"),
    FIRE("Fire damage"),
    FLOOD("Water/flood damage"),
    RECALL("Product recall"),
    OTHER("Other reasons");

    private final String description;

    WastageReason(String description) {
        this.description = description;
    }

}