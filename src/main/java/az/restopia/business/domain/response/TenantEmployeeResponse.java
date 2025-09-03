package az.restopia.business.domain.response;

import az.restopia.commons.domain.enums.DeleteStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantEmployeeResponse {
    private Long id;
    private String tenantCode;
    private Long businessId;
    private Long storeId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String role;
    private boolean isActive;
    private DeleteStatus deleteStatus;
}
