package az.restopia.business.service;

import az.restopia.business.domain.request.TenantCustomerRatingRequest;

public interface TenantCustomerRatingService {
    void createRating(TenantCustomerRatingRequest request);
}
