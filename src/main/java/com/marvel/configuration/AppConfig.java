package com.marvel.configuration;

import com.marvel.entity.UserEntity;
import com.marvel.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

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
                        .userName("admin")
                        .userPassword("123")
                        .build();
                userRepository.save(defaultUser);
                System.out.println("User: " + defaultUser);
            } else {
                System.out.println("User ready");
            }
        };
    }



}
