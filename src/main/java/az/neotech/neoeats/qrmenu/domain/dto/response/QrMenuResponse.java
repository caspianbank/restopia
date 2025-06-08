package az.neotech.neoeats.qrmenu.domain.dto.response;

import az.neotech.neoeats.menu.domain.response.MenuCategoryResponse;
import az.neotech.neoeats.menu.domain.response.MenuItemResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class QrMenuResponse {
    private String menuName;
    private String menuDescription;
    private List<MenuCategoryResponse> categories;
    private List<MenuItemResponse> items;
}
