package az.neotech.neoeats.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequest {

    @Size(max = 100)
    @NotBlank(message = "Menu name is required")
    private String name;

    @Size(max = 500)
    private String description;
}
