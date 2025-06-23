package az.neotech.neoeats.security.controller;

import az.neotech.neoeats.security.domain.response.AuthenticationTokenResponse;
import az.neotech.neoeats.security.domain.request.LoginRequest;
import az.neotech.neoeats.security.domain.request.RegisterRequest;
import az.neotech.neoeats.security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationTokenResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    // todo: update this method
    @PostMapping("/register")
    public ResponseEntity<AuthenticationTokenResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
}
