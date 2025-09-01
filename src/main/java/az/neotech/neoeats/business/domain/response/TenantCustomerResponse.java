package az.neotech.neoeats.business.domain.response;

import az.neotech.neoeats.commons.domain.enums.DeleteStatus;

import java.time.LocalDateTime;

public record TenantCustomerResponse(
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
