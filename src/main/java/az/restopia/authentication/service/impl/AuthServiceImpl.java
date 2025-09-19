package az.restopia.authentication.service.impl;

import az.restopia.authentication.domain.request.QrMenuLoginRequest;
import az.restopia.authentication.domain.response.AuthTokenResponse;
import az.restopia.authentication.service.AuthService;
import az.restopia.business.domain.entity.Tenant;
import az.restopia.business.service.TenantService;
import az.restopia.customer.domain.entity.Customer;
import az.restopia.customer.domain.request.CustomerRequest;
import az.restopia.customer.service.CustomerService;
import az.restopia.security.domain.request.TokenClaimsRequest;
import az.restopia.security.service.AuthTokenProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TenantService tenantService;
    private final CustomerService customerService;
    private final AuthTokenProviderService tokenProviderService;

    @Override
    public AuthTokenResponse loginOrCreateCustomer(String tenantCode, QrMenuLoginRequest request) {
        Tenant tenant = tenantService.getTenantByCodeOrElseThrow(tenantCode);

        final String combinedPhone = request.countryCode() + request.phoneNumber();

        Customer customer = customerService.getByPhoneAndTenantCode(combinedPhone, tenant.getTenantCode())
                .orElseGet(() -> createCustomer(tenantCode, request));

        var tokenClaimsRequest = new TokenClaimsRequest(tenantCode, combinedPhone, customer.getFullName());
        AuthTokenResponse authTokenResponse = tokenProviderService.generateTokens(tokenClaimsRequest);
        log.info("Tokens generated for customer: {}", customer.getFullName());
        return authTokenResponse;
    }

    private Customer createCustomer(String tenantCode, QrMenuLoginRequest request) {
        log.info("Customer not found, creating new customer: {}", request.fullName());
        var tenantCustomerRequest = new CustomerRequest(tenantCode, request.fullName(), request.countryCode(),
                request.phoneNumber());
        return customerService.createCustomer(tenantCustomerRequest);
    }
}
