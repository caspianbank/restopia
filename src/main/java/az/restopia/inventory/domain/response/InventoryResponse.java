package az.restopia.inventory.domain.response;

import az.neotech.commons.finance.Currency;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryResponse {

    private Long id;
    private String tenantCode;
    private Long businessId;
    private String businessName;
    private Long businessStoreId;
    private String businessStoreName;
    private String name;
    private String description;
    private Currency currency;
    private String location;
    private boolean main;
    private boolean requiresApprovalIn;
    private boolean requiresApprovalOut;
    private boolean businessLevel;
    private boolean storeLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}