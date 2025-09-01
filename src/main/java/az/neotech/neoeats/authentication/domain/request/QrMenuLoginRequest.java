package az.neotech.neoeats.authentication.domain.request;

import jakarta.validation.constraints.NotBlank;

public record QrMenuLoginRequest(
        @NotBlank(message = "Full name is required")
        String fullName,

        @NotBlank(message = "Country code is required")
        String countryCode,

        @NotBlank(message = "Phone number is required")
        String phoneNumber
) {

}