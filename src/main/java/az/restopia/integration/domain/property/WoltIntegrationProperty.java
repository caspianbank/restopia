package az.restopia.integration.domain.property;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.wolt-food.integration")
public record WoltIntegrationProperty(
        @NotBlank(message = "clientId cannot be blank")
        String clientId,

        @NotBlank(message = "clientSecret cannot be blank")
        String clientSecret
) {
}
