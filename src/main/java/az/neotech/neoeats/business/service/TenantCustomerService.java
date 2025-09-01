package az.neotech.neoeats.business.service;

import az.neotech.neoeats.business.domain.entity.TenantCustomer;
import az.neotech.neoeats.business.domain.request.TenantCustomerRequest;

import java.util.Optional;

public interface TenantCustomerService {

    TenantCustomer createCustomer(TenantCustomerRequest request);

    Optional<TenantCustomer> getByPhoneAndTenantCode(String phone, String tenantCode);
}
