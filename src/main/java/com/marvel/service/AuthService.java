package com.marvel.service;

import com.marvel.jwt.AuthResponse;
import com.marvel.jwt.LoginRequest;
import com.marvel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:ValidationsMessages.properties")
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Value("${not.found}")
    private String notFound;

    public AuthResponse login(LoginRequest request) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
        validateUserIfExists(request.getName());
        UserDetails user = userRepository.findByUserName(request.getName());
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    private void validateUserIfExists(final String name) throws Exception {
        if (userRepository.findByUserName(name) == null) {
            throw new Exception(notFound);
        }
    }

}
