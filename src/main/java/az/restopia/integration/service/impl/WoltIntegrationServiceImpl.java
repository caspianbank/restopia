package az.restopia.integration.service.impl;

import az.restopia.integration.client.WoltIntegrationClient;
import az.restopia.integration.domain.property.WoltIntegrationProperty;
import az.restopia.integration.domain.response.WoltTokenResponse;
import az.restopia.integration.service.WoltIntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
public class WoltIntegrationServiceImpl implements WoltIntegrationService {

    private final WoltIntegrationProperty property;
    private final WoltIntegrationClient woltIntegrationClient;

    @Override
    public void integrate(String code, String redirectUri) {
        String basicAuth = getEncodedBasicAuth();

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "authorization_code");
        form.add("redirect_uri", redirectUri);
        form.add("code", code);

        WoltTokenResponse response = woltIntegrationClient.retrieveTokens(basicAuth, form);
        log.debug("Tokens retrieved from Wolt API");
        // todo: get those tokens and store them in database, use tenantStore, tenantCode
    }

    @Override
    public void updateTokens(String refreshToken) {
        String basicAuth = getEncodedBasicAuth();

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "refresh_token");
        form.add("refresh_token", refreshToken);

        woltIntegrationClient.updateTokens(basicAuth, form);
        // todo: get those tokens and update them in database, use tenantStore, tenantCode
    }

    private String getEncodedBasicAuth() {
        return "Basic " + Base64.getEncoder()
                .encodeToString((property.clientId() + ":" + property.clientSecret()).getBytes(StandardCharsets.UTF_8));
    }

}
