package az.restopia.business.controller;

import az.restopia.business.domain.request.TenantCustomerRatingRequest;
import az.restopia.business.service.TenantCustomerRatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tenant-customer-ratings")
@RequiredArgsConstructor
public class TenantCustomerRatingController {

    private final TenantCustomerRatingService ratingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void submitRating(@Valid @RequestBody TenantCustomerRatingRequest request) {
        ratingService.createRating(request);
    }
}
