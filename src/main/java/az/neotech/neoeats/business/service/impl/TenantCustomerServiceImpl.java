package az.neotech.neoeats.business.service.impl;

import az.neotech.neoeats.business.domain.entity.TenantCustomer;
import az.neotech.neoeats.business.domain.mapper.TenantCustomerMapper;
import az.neotech.neoeats.business.domain.request.TenantCustomerRequest;
import az.neotech.neoeats.business.repository.TenantCustomerRepository;
import az.neotech.neoeats.business.service.TenantCustomerService;
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
    public Optional<TenantCustomer> getByPhoneAndTenantCode(String phone, String tenantCode) {
        return customerRepository.findByPhoneNumberAndTenantCode(phone, tenantCode);
    }
}
