package az.restopia.inventory.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCategoryRequest {
    @NotBlank
    private String tenantCode;

    private Long parentId;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Boolean isActive;

    @NotBlank
    private String lang;
}
