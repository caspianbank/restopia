package az.restopia.menu.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuCategoryRequest {
    private String name;
    private String description;
    private Integer position;
    private boolean isActive;
    private Long menuId;
}