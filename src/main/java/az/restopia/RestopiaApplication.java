package az.restopia;

import az.restopia.integration.domain.property.WoltIntegrationProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableConfigurationProperties(value = {
        WoltIntegrationProperty.class
})
@SpringBootApplication
public class RestopiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestopiaApplication.class, args);
    }

}
