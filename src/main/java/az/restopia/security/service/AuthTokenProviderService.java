package az.restopia.security.service;

import az.restopia.authentication.domain.response.AuthTokenResponse;
import az.restopia.security.domain.request.TokenClaimsRequest;

public interface AuthTokenProviderService {

    AuthTokenResponse generateTokens(TokenClaimsRequest tokenClaimsRequest);
}
