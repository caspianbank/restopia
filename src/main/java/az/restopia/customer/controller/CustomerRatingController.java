package az.restopia.customer.controller;

import az.restopia.customer.domain.request.CustomerRatingRequest;
import az.restopia.customer.service.CustomerRatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer-ratings")
@RequiredArgsConstructor
public class CustomerRatingController {

    private final CustomerRatingService ratingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void submitRating(@Valid @RequestBody CustomerRatingRequest request) {
        ratingService.createRating(request);
    }
}
