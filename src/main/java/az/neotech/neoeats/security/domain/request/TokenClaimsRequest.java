package az.neotech.neoeats.security.domain.request;

public record TokenClaimsRequest(
        String tenantCode,
        String phoneNumber,
        String fullName
) {
}
