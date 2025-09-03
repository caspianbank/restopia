package az.restopia.business.service.impl;

import az.restopia.business.domain.entity.TenantBusiness;
import az.restopia.business.domain.entity.TenantCustomer;
import az.restopia.business.domain.entity.TenantCustomerRating;
import az.restopia.business.domain.request.TenantCustomerRatingRequest;
import az.restopia.business.repository.TenantCustomerRatingRepository;
import az.restopia.business.service.TenantBusinessService;
import az.restopia.business.service.TenantCustomerRatingService;
import az.restopia.business.service.TenantCustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TenantCustomerRatingServiceImpl implements TenantCustomerRatingService {

    private final TenantCustomerRatingRepository ratingRepository;
    private final TenantBusinessService tenantBusinessService;
    private final TenantCustomerService customerService;

    @Override
    @Transactional
    public void createRating(TenantCustomerRatingRequest request) {
        TenantBusiness business = tenantBusinessService.getByIdOrThrow(request.tenantBusinessId());

        TenantCustomer customer = null;
        if (request.tenantCustomerId() != null) {
            customer = customerService.getById(request.tenantCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
        }

        TenantCustomerRating rating = new TenantCustomerRating();
        rating.setTenantBusiness(business);
        rating.setTenantCustomer(customer);
        rating.setRatingCategory(request.ratingType());
        rating.setValue(request.value());
        rating.setComment(request.comment());

        ratingRepository.save(rating);
        log.debug("Created rating: businessId={}, customer={}, category={}, value={}",
                business.getId(),
                customer != null ? customer.getId() : "anonymous",
                rating.getRatingCategory(),
                rating.getValue());
    }
}
