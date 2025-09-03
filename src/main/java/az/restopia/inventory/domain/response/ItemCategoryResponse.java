package az.restopia.inventory.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCategoryResponse {
    private Long id;
    private String tenantCode;
    private Long parentId;
    private String name;
    private String description;
    private Boolean isActive;
    private String lang;
}
