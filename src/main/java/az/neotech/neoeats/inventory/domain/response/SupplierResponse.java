package az.neotech.neoeats.inventory.domain.response;

public record SupplierResponse(
        Long id,
        String tenantCode,
        String name,
        String email,
        String phones,
        String address
) {
}
