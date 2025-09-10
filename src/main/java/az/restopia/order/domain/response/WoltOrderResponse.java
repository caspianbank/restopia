package az.restopia.order.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record WoltOrderResponse(
        String id,
        Venue venue,
        BasketPrice basketPrice,
        Delivery delivery,
        Fees fees,
        List<OrderItem> items,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
        Instant createdAt,

        String consumerComment,
        String consumerName,
        String consumerPhoneNumber,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant pickupEta,

        String attributionId,
        String type,
        String preOrder,
        String orderNumber,
        String orderStatus,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
        Instant modifiedAt,

        String companyTaxId,
        String loyaltyCardNumber,
        String cashPayment,
        OrderHandling orderHandling
) {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Venue(
            String id,
            String name,
            String externalVenueId
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record BasketPrice(
            Money total,
            PriceBreakdown priceBreakdown
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Money(
            Integer amount,
            String currency
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record PriceBreakdown(
            Money totalBeforeDiscounts,
            Money totalDiscounts,
            Money subtotalBasketDiscounts,
            Money subtotalItemDiscounts
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Delivery(
            String status,
            String type,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
            Instant time,
            Location location,
            Boolean selfDelivery
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Location(
            String streetAddress,
            String apartment,
            String city,
            String country,
            Coordinates coordinates,
            String formattedAddress
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Coordinates(BigDecimal lon, BigDecimal lat) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Fees(Money total, FeesPriceBreakdown priceBreakdown, List<FeePart> parts) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record FeesPriceBreakdown(
            Money totalBeforeDiscounts,
            Money totalDiscounts,
            Money liability
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record FeePart(
            String type,
            Money total,
            Integer vatPercentage
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record OrderItem(
            String itemType,
            String id,
            Integer count,
            String posId,
            String sku,
            String gtin,
            List<ItemOption> options,
            ItemPrice itemPrice,
            String name,
            Category category,
            Deposit deposit,
            Boolean isBundleOffer
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record ItemOption(
            String id,
            String name,
            String value,
            Money price,
            Integer vatPercentage,
            ItemPriceBreakdown priceBreakdown,
            String posId,
            Integer count,
            String valuePosId,
            Deposit deposit
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record ItemPrice(
            Money unitPrice,
            Money basePrice,
            Integer vatPercentage,
            Money total,
            ItemPriceBreakdown priceBreakdown
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record ItemPriceBreakdown(
            Money totalBeforeDiscounts,
            Money totalDiscounts,
            Money subtotalBasketDiscounts,
            Money subtotalItemDiscounts,
            Money basePriceBeforeDiscounts,
            Money unitPriceBeforeDiscounts,
            Money subtotalOptionsBasketDiscounts,
            Money subtotalOptionsItemDiscounts
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Category(
            String id,
            String name
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Deposit(
            Money grossPrice,
            Money netPrice,
            Integer vatPercentage
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record OrderHandling(
            PreparationTime preparationTime,
            String acceptanceStatus,
            String courierArrival
    ) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record PreparationTime(
            Integer preparationTimeSeconds,
            String preparationTimeSource
    ) {}
}