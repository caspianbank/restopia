package az.neotech.neoeats.business.service.impl;

import az.neotech.neoeats.business.domain.entity.TenantBusiness;
import az.neotech.neoeats.business.domain.entity.TenantCustomer;
import az.neotech.neoeats.business.domain.entity.TenantCustomerRating;
import az.neotech.neoeats.business.domain.request.TenantCustomerRatingRequest;
import az.neotech.neoeats.business.repository.TenantBusinessRepository;
import az.neotech.neoeats.business.repository.TenantCustomerRatingRepository;
import az.neotech.neoeats.business.service.TenantBusinessService;
import az.neotech.neoeats.business.service.TenantCustomerRatingService;
import az.neotech.neoeats.business.service.TenantCustomerService;
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
