package az.restopia.order.domain.response;

public record BusinessWoltVenueResponse(
        String tenantCode,
        String venueId,
        boolean active
) {
}
