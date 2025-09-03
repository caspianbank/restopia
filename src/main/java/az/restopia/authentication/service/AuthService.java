package az.restopia.authentication.service;

import az.restopia.authentication.domain.request.QrMenuLoginRequest;
import az.restopia.authentication.domain.response.AuthTokenResponse;

public interface AuthService {

    AuthTokenResponse loginOrCreateCustomer(String tenantCode, QrMenuLoginRequest request);
}
