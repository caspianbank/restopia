package az.restopia.customer.domain.response;

import az.restopia.commons.domain.enums.DeleteStatus;

import java.time.LocalDateTime;

public record CustomerResponse(
        String tenantCode,
        String fullName,
        String firstName,
        String lastName,
        String countryCode,
        String phoneNumber,
        String email,
        DeleteStatus deleteStatus,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {

}
