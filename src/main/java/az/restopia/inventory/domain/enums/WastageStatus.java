package az.restopia.inventory.domain.enums;

import lombok.Getter;

@Getter
public enum WastageStatus {
    REPORTED("Wastage reported, pending review"),
    UNDER_INVESTIGATION("Under investigation"),
    APPROVED("Approved and processed"),
    REJECTED("Rejected - not valid wastage"),
    PARTIALLY_APPROVED("Partially approved"),
    INSURANCE_PENDING("Insurance claim pending"),
    CLOSED("Closed and finalized");

    private final String description;

    WastageStatus(String description) {
        this.description = description;
    }

}