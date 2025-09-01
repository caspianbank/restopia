package az.neotech.neoeats.authentication.service;

import az.neotech.neoeats.authentication.domain.request.QrMenuLoginRequest;
import az.neotech.neoeats.authentication.domain.response.AuthTokenResponse;

public interface AuthService {

    AuthTokenResponse loginOrCreateCustomer(String tenantCode, QrMenuLoginRequest request);
}
