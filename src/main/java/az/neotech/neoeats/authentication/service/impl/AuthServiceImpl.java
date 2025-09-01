package az.neotech.neoeats.authentication.service.impl;

import az.neotech.neoeats.authentication.domain.request.QrMenuLoginRequest;
import az.neotech.neoeats.authentication.domain.response.AuthTokenResponse;
import az.neotech.neoeats.authentication.service.AuthService;
import az.neotech.neoeats.business.domain.entity.Tenant;
import az.neotech.neoeats.business.domain.entity.TenantCustomer;
import az.neotech.neoeats.business.domain.request.TenantCustomerRequest;
import az.neotech.neoeats.business.service.TenantCustomerService;
import az.neotech.neoeats.business.service.TenantService;
import az.neotech.neoeats.security.domain.request.TokenClaimsRequest;
import az.neotech.neoeats.security.service.AuthTokenProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TenantService tenantService;
    private final TenantCustomerService tenantCustomerService;
    private final AuthTokenProviderService tokenProviderService;

    @Override
    public AuthTokenResponse loginOrCreateCustomer(String tenantCode, QrMenuLoginRequest request) {
        Tenant tenant = tenantService.getTenantByCodeOrElseThrow(tenantCode);

        final String combinedPhone = request.countryCode() + request.phoneNumber();

        TenantCustomer customer = tenantCustomerService.getByPhoneAndTenantCode(combinedPhone, tenant.getTenantCode())
                .orElseGet(() -> createCustomer(tenantCode, request));

        var tokenClaimsRequest = new TokenClaimsRequest(tenantCode, combinedPhone, customer.getFullName());
        AuthTokenResponse authTokenResponse = tokenProviderService.generateTokens(tokenClaimsRequest);
        log.info("Tokens generated for customer: {}", customer.getFullName());
        return authTokenResponse;
    }

    private TenantCustomer createCustomer(String tenantCode, QrMenuLoginRequest request) {
        log.info("Customer not found, creating new customer: {}", request.fullName());
        var tenantCustomerRequest = new TenantCustomerRequest(tenantCode, request.fullName(), request.countryCode(),
                request.phoneNumber());
        return tenantCustomerService.createCustomer(tenantCustomerRequest);
    }
}
