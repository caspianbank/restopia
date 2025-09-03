package az.restopia.security.domain.request;

public record TokenClaimsRequest(
        String tenantCode,
        String phoneNumber,
        String fullName
) {
}
