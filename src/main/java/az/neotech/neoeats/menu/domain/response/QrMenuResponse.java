package az.neotech.neoeats.menu.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QrMenuResponse {
    private String menuName;
    private String menuDescription;
    private List<MenuCategoryResponse> categories;
    private List<MenuItemResponse> items;
}
