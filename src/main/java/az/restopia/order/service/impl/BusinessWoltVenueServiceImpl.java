package az.restopia.order.service.impl;

import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.order.domain.response.BusinessWoltVenueResponse;
import az.restopia.order.repository.BusinessWoltVenueRepository;
import az.restopia.order.service.BusinessWoltVenueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class BusinessWoltVenueServiceImpl implements BusinessWoltVenueService {

    private final BusinessWoltVenueRepository businessWoltVenueRepository;

    @Override
    public BusinessWoltVenueResponse getBusinessWoltVenueOrThrow(String venueId) {
        return businessWoltVenueRepository.findByVenueId(venueId)
                .map(venue -> new BusinessWoltVenueResponse(venue.getTenantCode(), venue.getVenueId(), venue.isActive()))
                .orElseThrow(() -> new RecordNotFoundException("Venue not found! Either business does not exist or wolt app status is off."));
    }
}
