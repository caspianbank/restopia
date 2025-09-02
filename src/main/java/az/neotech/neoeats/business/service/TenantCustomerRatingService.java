package az.neotech.neoeats.business.service;

import az.neotech.neoeats.business.domain.request.TenantCustomerRatingRequest;

public interface TenantCustomerRatingService {
    void createRating(TenantCustomerRatingRequest request);
}
