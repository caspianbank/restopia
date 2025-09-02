package az.neotech.neoeats.business.domain.request;

import az.neotech.neoeats.business.domain.enums.RatingType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TenantCustomerRatingRequest(
        @NotNull
        Long tenantBusinessId,

        Long tenantCustomerId,

        @NotNull
        RatingType ratingType,

        @NotNull
        @Min(1)
        @Max(5)
        Integer value,

        String comment
) {
}
