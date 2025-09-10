package az.restopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestopiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestopiaApplication.class, args);
    }

}
