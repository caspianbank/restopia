package az.neotech.neoeats.inventory.domain.request;

public record SupplierRequest(
        String tenantCode,
        String name,
        String email,
        String phones,
        String address
) {
}
