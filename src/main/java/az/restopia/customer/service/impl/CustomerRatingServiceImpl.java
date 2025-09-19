package az.restopia.customer.service.impl;

import az.restopia.business.domain.entity.TenantBusiness;
import az.restopia.business.service.TenantBusinessService;
import az.restopia.customer.domain.entity.Customer;
import az.restopia.customer.domain.entity.CustomerRating;
import az.restopia.customer.domain.request.CustomerRatingRequest;
import az.restopia.customer.repository.CustomerRatingRepository;
import az.restopia.customer.service.CustomerRatingService;
import az.restopia.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerRatingServiceImpl implements CustomerRatingService {

    private final CustomerRatingRepository ratingRepository;
    private final TenantBusinessService tenantBusinessService;
    private final CustomerService customerService;

    @Override
    @Transactional
    public void createRating(CustomerRatingRequest request) {
        TenantBusiness business = tenantBusinessService.getByIdOrThrow(request.tenantBusinessId());

        Customer customer = null;
        if (request.tenantCustomerId() != null) {
            customer = customerService.getById(request.tenantCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
        }

        CustomerRating rating = new CustomerRating();
        rating.setTenantBusiness(business);
        rating.setCustomer(customer);
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
