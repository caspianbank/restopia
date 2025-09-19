package az.restopia.order.service;

import az.restopia.order.domain.response.BusinessWoltVenueResponse;

public interface BusinessWoltVenueService {

    BusinessWoltVenueResponse getBusinessWoltVenueOrThrow(String venueId);

}
