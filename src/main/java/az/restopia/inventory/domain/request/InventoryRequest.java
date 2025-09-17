package az.restopia.inventory.domain.request;

import az.neotech.commons.finance.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequest {

    @NotBlank(message = "Tenant code is required")
    @Size(max = 50, message = "Tenant code must not exceed 50 characters")
    private String tenantCode;

    @NotNull(message = "Business ID is required")
    private Long businessId;

    private Long businessStoreId;

    @NotBlank(message = "Inventory name is required")
    @Size(max = 200, message = "Inventory name must not exceed 200 characters")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @Size(max = 200, message = "Location must not exceed 200 characters")
    private String location;

    private Currency currency = Currency.AZN;

    private boolean main;

    private boolean requiresApprovalIn;

    private boolean requiresApprovalOut;
}