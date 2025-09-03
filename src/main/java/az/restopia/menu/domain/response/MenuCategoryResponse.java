package az.restopia.menu.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuCategoryResponse {
    private Long id;
    private String name;
    private String description;
    private Integer orderNumber;
    private Boolean isActive;
    private Long menuId;
}
