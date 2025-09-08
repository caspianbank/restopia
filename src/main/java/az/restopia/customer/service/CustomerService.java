package az.restopia.customer.service;

import az.restopia.customer.domain.entity.Customer;
import az.restopia.customer.domain.request.CustomerRequest;
import az.restopia.customer.domain.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {

    Customer createCustomer(CustomerRequest request);

    CustomerResponse createAndMapToResponse(CustomerRequest request);

    Optional<Customer> getById(Long id);

    Optional<Customer> getByPhoneAndTenantCode(String phone, String tenantCode);

    Page<CustomerResponse> getAllCustomers(Pageable pageable);

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomer(Long id, CustomerRequest request);

    void deleteCustomer(Long id);

    Page<CustomerResponse> searchCustomers(String fullName, String firstName, String lastName,
                                           String email, String phoneNumber, Pageable pageable);
}
