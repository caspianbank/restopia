package az.neotech.neoeats.security.domain.response;

import az.neotech.neoeats.security.domain.enums.TokenType;
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
