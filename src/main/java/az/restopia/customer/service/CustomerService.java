package az.restopia.customer.service;

import az.restopia.customer.domain.entity.Customer;
import az.restopia.customer.domain.request.CustomerRequest;

import java.util.Optional;

public interface CustomerService {

    Customer createCustomer(CustomerRequest request);

    Optional<Customer> getById(Long id);

    Optional<Customer> getByPhoneAndTenantCode(String phone, String tenantCode);
}
