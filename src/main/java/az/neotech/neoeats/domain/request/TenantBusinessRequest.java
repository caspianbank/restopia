package az.neotech.neoeats.domain.request;

import az.neotech.neoeats.domain.enums.BusinessType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantBusinessRequest {

    @NotBlank
    private String tenantCode;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BusinessType businessType;

    private String logoUrl;
}
