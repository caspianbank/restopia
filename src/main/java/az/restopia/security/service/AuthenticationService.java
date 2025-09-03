package az.restopia.security.service;

import az.restopia.security.domain.request.LoginRequest;
import az.restopia.security.domain.request.RegisterRequest;
import az.restopia.security.domain.response.AuthenticationTokenResponse;

public interface AuthenticationService {
    AuthenticationTokenResponse login(LoginRequest request);

    AuthenticationTokenResponse register(RegisterRequest request);
}
