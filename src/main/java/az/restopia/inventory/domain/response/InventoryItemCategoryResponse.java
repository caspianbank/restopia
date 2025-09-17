package az.restopia.inventory.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryItemCategoryResponse {

    private Long id;
    private String tenantCode;
    private String name;
    private String description;
    private Long parentId;
    private String parentName;
    private Integer sortOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}