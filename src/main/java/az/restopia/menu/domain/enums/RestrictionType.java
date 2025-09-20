package az.restopia.menu.domain.enums;

public enum RestrictionType {
    EXCLUDE_GROUP,      // If choice A is selected, exclude entire group B
    EXCLUDE_CHOICES,    // If choice A is selected, exclude specific choices in group B
    REQUIRE_GROUP,      // If choice A is selected, group B becomes required
    REQUIRE_CHOICES     // If choice A is selected, specific choices in group B become required
}