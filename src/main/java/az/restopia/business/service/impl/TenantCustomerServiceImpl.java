package az.restopia.business.service.impl;

import az.restopia.business.domain.entity.TenantCustomer;
import az.restopia.business.domain.mapper.TenantCustomerMapper;
import az.restopia.business.domain.request.TenantCustomerRequest;
import az.restopia.business.repository.TenantCustomerRepository;
import az.restopia.business.service.TenantCustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TenantCustomerServiceImpl implements TenantCustomerService {

    private final TenantCustomerMapper customerMapper;
    private final TenantCustomerRepository customerRepository;

    @Override
    @Transactional
    public TenantCustomer createCustomer(TenantCustomerRequest request) {
        log.info("Creating customer with tenantCode={} and email={}", request.tenantCode(), request.email());
        TenantCustomer saved = customerRepository.save(customerMapper.toEntity(request));
        log.debug("Created customer with id={}", saved.getId());
        return saved;
    }

    @Override
    public Optional<TenantCustomer> getById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<TenantCustomer> getByPhoneAndTenantCode(String phone, String tenantCode) {
        return customerRepository.findByPhoneNumberAndTenantCode(phone, tenantCode);
    }
}
