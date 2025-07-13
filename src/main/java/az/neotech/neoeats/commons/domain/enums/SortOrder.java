package az.neotech.neoeats.commons.domain.enums;

import lombok.Getter;

@Getter
public enum SortOrder {
    ASCENDING("ASC"),
    DESCENDING("DESC");

    private final String value;

    SortOrder(String value) {
        this.value = value;
    }
}
