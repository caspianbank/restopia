package az.restopia.customer.service.impl;

import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.commons.exception.RecordNotFoundException;
import az.restopia.customer.domain.entity.Customer;
import az.restopia.customer.domain.mapper.CustomerMapper;
import az.restopia.customer.domain.request.CustomerRequest;
import az.restopia.customer.domain.response.CustomerResponse;
import az.restopia.customer.repository.CustomerRepository;
import az.restopia.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer createCustomer(CustomerRequest request) {
        log.info("Creating new customer with email: {}", request.email());
        Customer customer = customerMapper.toEntity(request);
        customer.setDeleteStatus(DeleteStatus.ACTIVE);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer created successfully with id: {}", savedCustomer.getId());
        return savedCustomer;
    }

    @Override
    public CustomerResponse createAndMapToResponse(CustomerRequest request) {
        Customer customer = createCustomer(request);
        return customerMapper.toResponse(customer);
    }

    @Override
    public Optional<Customer> getById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> getByPhoneAndTenantCode(String phone, String tenantCode) {
        return customerRepository.findByPhoneNumberAndTenantCode(phone, tenantCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        log.info("Getting all customers with pageable: {}", pageable);
        Page<Customer> customers = customerRepository.findByDeleteStatus(DeleteStatus.ACTIVE, pageable);
        return customers.map(customerMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(Long id) {
        log.info("Getting customer by id: {}", id);
        Customer customer = customerRepository.findByIdAndDeleteStatus(id, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Customer not found with id: " + id));
        return customerMapper.toResponse(customer);
    }


    @Override
    @Transactional
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        log.info("Updating customer with id: {}", id);
        Customer existingCustomer = customerRepository.findByIdAndDeleteStatus(id, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Customer not found with id: " + id));

        customerMapper.updateEntity(request, existingCustomer);
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        log.info("Customer updated successfully with id: {}", updatedCustomer.getId());
        return customerMapper.toResponse(updatedCustomer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        log.info("Deleting customer with id: {}", id);
        Customer customer = customerRepository.findByIdAndDeleteStatus(id, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RecordNotFoundException("Customer not found with id: " + id));

        customer.setDeleteStatus(DeleteStatus.DELETED);
        customerRepository.save(customer);
        log.info("Customer deleted successfully with id: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerResponse> searchCustomers(String fullName, String firstName, String lastName,
                                                  String email, String phoneNumber, Pageable pageable) {
        log.info("Searching customers with filters - fullName: {}, firstName: {}, lastName: {}, email: {}, phoneNumber: {}",
                fullName, firstName, lastName, email, phoneNumber);

        Page<Customer> customers = customerRepository.searchCustomers(
                fullName, firstName, lastName, email, phoneNumber, DeleteStatus.ACTIVE, pageable);
        return customers.map(customerMapper::toResponse);
    }
}
