package az.restopia.customer.service;

import az.restopia.customer.domain.request.CustomerRatingRequest;

public interface CustomerRatingService {
    void createRating(CustomerRatingRequest request);
}
