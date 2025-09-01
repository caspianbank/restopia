package az.neotech.neoeats.authentication.controller;

import az.neotech.neoeats.authentication.domain.request.QrMenuLoginRequest;
import az.neotech.neoeats.authentication.domain.response.AuthTokenResponse;
import az.neotech.neoeats.authentication.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/qr-menu/login")
    public AuthTokenResponse login(
            @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode,
            @Valid @RequestBody QrMenuLoginRequest request
    ) {
        return authService.loginOrCreateCustomer(tenantCode, request);
    }
}
