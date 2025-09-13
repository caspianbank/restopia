package az.restopia.integration.domain.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record WoltTokenResponse(
        String accessToken,
        String tokenType,
        String refreshToken,
        long expiresIn,
        String scope
) {}