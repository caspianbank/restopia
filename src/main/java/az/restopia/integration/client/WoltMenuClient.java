package az.restopia.integration.client;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "client-wolt-food",
        url = "${feign.client.wolt-food}"
)
public interface WoltMenuClient {

    /**
     * Create or update the complete menu for a restaurant
     */
    @PostMapping("/v1/restaurants/{venueId}/menu")
    ResponseEntity<MenuResponse> createOrUpdateMenu(
            @PathVariable("venueId") String venueId,
            @RequestBody MenuRequest request
    );

    /**
     * Update inventory status for menu items (availability, stock levels)
     */
    @PatchMapping("/venues/{venueId}/items/inventory")
    ResponseEntity<Void> updateItemsInventory(
            @PathVariable("venueId") String venueId,
            @RequestBody ItemsInventoryUpdateRequest request
    );

    /**
     * Update menu items (prices, descriptions, etc.)
     */
    @PatchMapping("/venues/{venueId}/items")
    ResponseEntity<Void> updateItems(
            @PathVariable("venueId") String venueId,
            @RequestBody ItemsUpdateRequest request
    );

    /**
     * Update option values for menu items (modifiers, extras, variations)
     */
    @PatchMapping("/venues/{venueId}/options/values")
    ResponseEntity<Void> updateOptionValues(
            @PathVariable("venueId") String venueId,
            @RequestBody OptionValuesUpdateRequest request
    );

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    record MenuRequest(
            String id,
            String currency,
            String primaryLanguage,
            List<Category> categories,
            List<Item> items
    ) {
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record Category(
                String id,
                List<LocalizedText> name,
                List<LocalizedText> description,
                List<Subcategory> subcategories,
                List<Item> items,
                List<WeeklyAvailability> weeklyAvailability
        ) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record Subcategory(
                List<LocalizedText> name,
                List<LocalizedText> description,
                List<Item> items
        ) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record Item(
                List<LocalizedText> name,
                List<LocalizedText> description,
                String imageUrl,
                Integer price,
                Double salesTaxPercentage,
                Double alcoholPercentage,
                CaffeineContent caffeineContent,
                List<WeeklyAvailability> weeklyAvailability,
                List<WeeklyAvailability> weeklyVisibility,
                Boolean enabled,
                Discount discounted,
                String disabledUntil,
                List<String> deliveryMethods,
                List<Option> options,
                String externalData,
                Integer quantity,
                ProductInformation productInformation,
                String gtinBarcode,
                String merchantSku,
                Deposit deposit,
                Double vatPercentage,
                Boolean bundleOffer,
                Restrictions restrictions
        ) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record LocalizedText(String lang, String value) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record CaffeineContent(String servingSize, Double value) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record WeeklyAvailability(String openingDay, String openingTime, String closingDay, String closingTime) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record Discount(Integer originalPrice, String discountType) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record Option(
                List<LocalizedText> name,
                String type,
                SelectionRange selectionRange,
                String externalData,
                List<OptionValue> values
        ) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record SelectionRange(Integer max, Integer min) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record OptionValue(
                List<LocalizedText> name,
                SelectionRange selectionRange,
                Integer price,
                Boolean enabled,
                Boolean defaultValue,
                String externalData,
                List<SubOptionValue> subOptionValues,
                ProductInformation productInformation,
                String gtinBarcode,
                String merchantSku,
                Deposit deposit,
                Double vatPercentage
        ) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record SubOptionValue(
                List<LocalizedText> name,
                SelectionRange selectionRange,
                Integer price,
                Boolean enabled,
                Boolean defaultValue,
                String externalData,
                ProductInformation productInformation,
                String gtinBarcode,
                String merchantSku,
                Deposit deposit,
                Double vatPercentage
        ) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record ProductInformation(
                List<LocalizedText> ingredients,
                List<LocalizedText> additives,
                List<LocalizedText> allergens,
                List<LocalizedText> producerInformation,
                List<LocalizedText> distributorInformation,
                List<LocalizedText> countryOfOrigin,
                List<LocalizedText> conditionsOfUseAndStorage,
                List<LocalizedText> nutritionFacts,
                List<LocalizedText> regulatoryInformation,
                Integer weightInG,
                Integer volumeInMl,
                Integer quantity,
                String displayUnit
        ) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record Deposit(Integer price, Double vatPercentage) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record Restrictions(List<String> itemRestrictions, ItemAgeRestriction itemAgeRestriction) {}
        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
        public record ItemAgeRestriction(Integer limit) {}
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    record MenuResponse(String status, String message) {}

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    record ItemsInventoryUpdateRequest(List<ItemInventory> data) {
        public record ItemInventory(String sku, Integer inventory) {}
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    record ItemsUpdateRequest(List<Item> data) {
        public record Item(
                String externalId,
                Integer price,
                Integer discountedPrice,
                Boolean enabled,
                String disabledUntil,
                Boolean inStock,
                String imageUrl
        ) {}
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    record OptionValuesUpdateRequest(List<OptionValue> data) {
        public record OptionValue(
                String externalId,
                Integer price,
                Boolean enabled
        ) {}
    }
}
