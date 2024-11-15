package com.marvel.configuration;

import com.marvel.entity.UserEntity;
import com.marvel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository userRepository;

    @Bean
    public WebClient marvelWebClient() {
        return WebClient.builder()
                .baseUrl("https://gateway.marvel.com:443/v1/public/")
                .build();
    }

    // Create a default User to test the API

    @Bean
    public CommandLineRunner initializeUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                UserEntity defaultUser = UserEntity.builder()
                        .userName("Admin")
                        .userPassword("$2a$10$xu5Y1rV/ovJH2hQuNGeJou3Hs3o1uRxYeUudfJwSu/ztzLQm2muiG")
                        .build();
                userRepository.save(defaultUser);
                System.out.println("User: " + defaultUser);
            } else {
                System.out.println("User ready");
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailService() {
        return userRepository::findByUserName;
    }


}
