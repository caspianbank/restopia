package az.restopia.business.service;

import az.restopia.business.domain.entity.TenantCustomer;
import az.restopia.business.domain.request.TenantCustomerRequest;

import java.util.Optional;

public interface TenantCustomerService {

    TenantCustomer createCustomer(TenantCustomerRequest request);

    Optional<TenantCustomer> getById(Long id);

    Optional<TenantCustomer> getByPhoneAndTenantCode(String phone, String tenantCode);
}
