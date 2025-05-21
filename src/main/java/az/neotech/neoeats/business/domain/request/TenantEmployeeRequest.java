package az.neotech.neoeats.business.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TenantEmployeeRequest {
    private Long businessId;
    private Long storeId;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> phoneNumbers;
    private String role;
}
