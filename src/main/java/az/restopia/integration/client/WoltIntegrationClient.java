package az.restopia.integration.client;

import az.restopia.integration.config.WoltIntegrationFeignConfig;
import az.restopia.integration.domain.response.WoltTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "client-wolt-food-integration",
        url = "${feign.client.wolt-food-integration}",
        configuration = WoltIntegrationFeignConfig.class
)
public interface WoltIntegrationClient {

    @PostMapping(value = "/oauth2/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    WoltTokenResponse retrieveTokens(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String basicAuth,
            @RequestBody MultiValueMap<String, String> form
    );

    @PostMapping(value = "/oauth2/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    WoltTokenResponse updateTokens(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String basicAuth,
            @RequestBody MultiValueMap<String, String> form
    );

}
