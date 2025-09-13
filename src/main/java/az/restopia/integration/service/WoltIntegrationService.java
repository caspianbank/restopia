package az.restopia.integration.service;

public interface WoltIntegrationService {

    void integrate(String code, String redirectUri);

    void updateTokens(String refreshToken);

}
