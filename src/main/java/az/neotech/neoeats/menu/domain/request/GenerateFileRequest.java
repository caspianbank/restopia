package az.neotech.neoeats.menu.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record GenerateFileRequest(
        @NotBlank
        String tenantCode,

        @NotNull
        @Size(min = 1)
        List<String> codes
) {

}
