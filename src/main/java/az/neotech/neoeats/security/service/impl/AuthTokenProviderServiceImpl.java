package az.neotech.neoeats.security.service.impl;

import az.neotech.neoeats.authentication.domain.response.AuthTokenResponse;
import az.neotech.neoeats.security.domain.request.TokenClaimsRequest;
import az.neotech.neoeats.security.service.AuthTokenProviderService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Slf4j
@Service
public class AuthTokenProviderServiceImpl implements AuthTokenProviderService {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public AuthTokenResponse generateTokens(TokenClaimsRequest tokenClaimsRequest) {
        Instant now = Instant.now();

        long accessTokenValidityMinutes = 15;
        Instant accessExpiry = now.plus(accessTokenValidityMinutes, ChronoUnit.MINUTES);
        long refreshTokenValidityDays = 30;
        Instant refreshExpiry = now.plus(refreshTokenValidityDays, ChronoUnit.DAYS);

        var claims = Map.of(
                "tenant", tokenClaimsRequest.tenantCode(),
                "fullName", tokenClaimsRequest.fullName()
        );
        String accessToken = Jwts.builder()
                .setSubject(tokenClaimsRequest.phoneNumber())
                .addClaims(claims)
                .setIssuedAt(java.util.Date.from(now))
                .setExpiration(java.util.Date.from(accessExpiry))
                .signWith(secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(tokenClaimsRequest.phoneNumber())
                .addClaims(claims)
                .setIssuedAt(java.util.Date.from(now))
                .setExpiration(java.util.Date.from(refreshExpiry))
                .signWith(secretKey)
                .compact();

        return new AuthTokenResponse(accessToken, refreshToken, accessExpiry, refreshExpiry);
    }
}
