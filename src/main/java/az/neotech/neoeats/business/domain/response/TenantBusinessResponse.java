package az.neotech.neoeats.business.domain.response;

import az.neotech.neoeats.business.domain.enums.BusinessType;
import az.neotech.neoeats.commons.enums.DeleteStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantBusinessResponse {

    private Long id;
    private String tenantCode;
    private String name;
    private String description;
    private BusinessType businessType;
    private String logoUrl;
    private DeleteStatus deleteStatus;
}
