package az.restopia.customer.domain.request;

import az.restopia.business.domain.enums.RatingType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CustomerRatingRequest(
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
