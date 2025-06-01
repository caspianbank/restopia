package az.neotech.neoeats.menu.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {

    private String name;
    private String description;
    private boolean isActive;
}
