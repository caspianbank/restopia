package az.restopia.inventory.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryItemCategoryRequest {

    @NotBlank(message = "Tenant code is required")
    @Size(max = 50, message = "Tenant code cannot exceed 50 characters")
    private String tenantCode;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    private String description;

    private Long parentId;

    private Integer sortOrder = 0;
}