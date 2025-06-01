package az.neotech.neoeats.menu.domain.response;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int position;
    private boolean isActive;
    private Long menuCategoryId;
}
