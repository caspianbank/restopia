package az.neotech.neoeats.menu.domain.request;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private int position;
    private boolean isActive;
    private Long menuCategoryId;
}
