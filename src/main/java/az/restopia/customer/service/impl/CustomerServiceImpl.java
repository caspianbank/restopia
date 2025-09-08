package az.restopia.customer.service.impl;

import az.restopia.customer.domain.entity.Customer;
import az.restopia.customer.domain.mapper.CustomerMapper;
import az.restopia.customer.domain.request.CustomerRequest;
import az.restopia.customer.repository.CustomerRepository;
import az.restopia.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        log.info("Creating customer with tenantCode={} and email={}", request.tenantCode(), request.email());
        Customer saved = customerRepository.save(customerMapper.toEntity(request));
        log.debug("Created customer with id={}", saved.getId());
        return saved;
    }

    @Override
    public Optional<Customer> getById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> getByPhoneAndTenantCode(String phone, String tenantCode) {
        return customerRepository.findByPhoneNumberAndTenantCode(phone, tenantCode);
    }
}
