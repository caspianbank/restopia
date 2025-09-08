package az.restopia.customer.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
        @NotBlank(message = "Tenant code is required")
        String tenantCode,

        String fullName,

        String firstName,

        String lastName,

        @Size(max = 5, message = "Country code cannot exceed 5 characters")
        String countryCode,

        @Size(max = 20, message = "Phone number cannot exceed 20 characters")
        String phoneNumber,

        @Email(message = "Invalid email format")
        @Size(max = 100, message = "Email cannot exceed 100 characters")
        String email
) {

    public CustomerRequest(String tenantCode, String fullName, String countryCode, String phoneNumber) {
        this(tenantCode, fullName, null, null, countryCode, phoneNumber, null);
    }

}
