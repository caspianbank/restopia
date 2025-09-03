package az.restopia.security.domain.response;

import az.restopia.security.domain.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationTokenResponse {
    private String token;
    private TokenType tokenType;
}
