package az.neotech.neoeats.security.service.impl;

import az.neotech.neoeats.security.domain.entity.Role;
import az.neotech.neoeats.security.domain.entity.User;
import az.neotech.neoeats.security.domain.enums.TokenType;
import az.neotech.neoeats.security.domain.request.LoginRequest;
import az.neotech.neoeats.security.domain.request.RegisterRequest;
import az.neotech.neoeats.security.domain.response.AuthenticationTokenResponse;
import az.neotech.neoeats.security.repository.RoleRepository;
import az.neotech.neoeats.security.repository.UserRepository;
import az.neotech.neoeats.security.service.AuthenticationService;
import az.neotech.neoeats.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthenticationTokenResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        return new AuthenticationTokenResponse(token, TokenType.BEARER);
    }

    @Override
    @Transactional
    public AuthenticationTokenResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setTenantCode(request.getTenantCode());

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);

        // Generate token for the new user
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(Role::getName)
                        .map(SimpleGrantedAuthority::new)
                        .toList()
        );

        String token = jwtUtil.generateToken(userDetails);
        return new AuthenticationTokenResponse(token, TokenType.BEARER);
    }

}
