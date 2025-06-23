package az.neotech.neoeats.security.service;

import az.neotech.neoeats.security.domain.request.LoginRequest;
import az.neotech.neoeats.security.domain.request.RegisterRequest;
import az.neotech.neoeats.security.domain.response.AuthenticationTokenResponse;

public interface AuthenticationService {
    AuthenticationTokenResponse login(LoginRequest request);

    AuthenticationTokenResponse register(RegisterRequest request);
}
