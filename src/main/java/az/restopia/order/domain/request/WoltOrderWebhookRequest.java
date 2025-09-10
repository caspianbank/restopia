package az.restopia.order.domain.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record WoltOrderWebhookRequest(
        String event,
        String orderId,
        String venueId,
        String resourceUrl
) {
}