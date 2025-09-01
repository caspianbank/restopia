package az.neotech.neoeats.security.service;

import az.neotech.neoeats.authentication.domain.response.AuthTokenResponse;
import az.neotech.neoeats.security.domain.request.TokenClaimsRequest;

public interface AuthTokenProviderService {

    AuthTokenResponse generateTokens(TokenClaimsRequest tokenClaimsRequest);
}
