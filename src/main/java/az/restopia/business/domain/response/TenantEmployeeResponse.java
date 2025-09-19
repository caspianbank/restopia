package az.restopia.business.domain.response;

import az.restopia.commons.domain.enums.DeleteStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TenantEmployeeResponse {
    private Long id;
    private String tenantCode;
    private Long businessId;
    private String businessName;
    private Long storeId;
    private String storeName;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String role;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private DeleteStatus deleteStatus;
}
