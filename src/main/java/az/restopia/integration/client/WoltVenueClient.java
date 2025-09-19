package az.restopia.integration.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "client-wolt-food",
        url = "${feign.client.wolt-food}"
)
public interface WoltVenueClient {

    /**
     * Get venue status information including online status, opening times, and delivery area
     */
    @GetMapping("/venues/{venueId}/status")
    ResponseEntity<VenueStatusResponse> getVenueStatus(@PathVariable("venueId") String venueId);

    /**
     * Get current delivery provider configuration for the venue
     */
    @GetMapping("/venues/{venueId}/delivery-provider")
    ResponseEntity<DeliveryProviderResponse> getDeliveryProvider(@PathVariable("venueId") String venueId);

    /**
     * Update delivery provider settings for the venue
     */
    @PatchMapping("/venues/{venueId}/delivery-provider")
    ResponseEntity<Void> updateDeliveryProvider(
            @PathVariable("venueId") String venueId,
            @RequestBody DeliveryProviderUpdateRequest request
    );

    /**
     * Update venue online/offline status
     */
    @PatchMapping("/venues/{venueId}/online")
    ResponseEntity<Void> updateOnlineStatus(
            @PathVariable("venueId") String venueId,
            @RequestBody OnlineStatusUpdateRequest request
    );

    /**
     * Update venue opening times
     */
    @PatchMapping("/venues/{venueId}/opening-times")
    ResponseEntity<Void> updateOpeningTimes(
            @PathVariable("venueId") String venueId,
            @RequestBody OpeningTimesUpdateRequest request
    );

    /**
     * Set special opening times (holidays, exceptional hours, etc.)
     */
    @PutMapping(value = "/venues/{venueId}/special-opening-times", consumes = "text/plain")
    ResponseEntity<Void> setSpecialOpeningTimes(
            @PathVariable("venueId") String venueId,
            @RequestBody String specialTimesData
    );

    // DTO Classes for request/response bodies

    record VenueStatusResponse(
            String externalVenueId,
            VenueStatus status,
            ContactDetails contactDetails,
            OpeningTime[] openingTimes,
            SpecialTime[] specialTimes,
            DeliveryArea[] deliveryArea,
            String[] lastThreeOrdersStatus
    ) {}

    record VenueStatus(
            boolean isOpen,
            boolean isOnline,
            boolean isIpadFree
    ) {}

    record ContactDetails(
            String address,
            String phone
    ) {}

    record OpeningTime(
            String openingDay,
            String openingTime,
            String closingDay,
            String closingTime
    ) {}

    record SpecialTime(
            String openingDate,
            String openingTime,
            String closingDate,
            String closingTime
    ) {}

    record DeliveryArea(
            double lat,
            double lng
    ) {}

    record DeliveryProviderResponse(
            String deliveryProvider
    ) {}

    record DeliveryProviderUpdateRequest(
            String deliveryProvider
    ) {}

    record OnlineStatusUpdateRequest(
            String status,
            String until
    ) {}

    record OpeningTimesUpdateRequest(
            OpeningTime[] availability
    ) {}
}