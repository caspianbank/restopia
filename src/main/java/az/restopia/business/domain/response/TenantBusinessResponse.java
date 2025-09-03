package az.restopia.business.domain.response;

import az.restopia.business.domain.enums.BusinessType;
import az.restopia.commons.domain.enums.DeleteStatus;
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
