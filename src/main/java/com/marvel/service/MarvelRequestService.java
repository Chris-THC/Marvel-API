package com.marvel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MarvelRequestService {
    private final WebClient webClient;

    @Autowired
    public MarvelRequestService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://gateway.marvel.com:443/v1/public/")
                .build();
    }

    public Mono<String> getAllCharacters() {
        String url = "characters?apikey=bf300db1f72ad8ed3885f148bddfe897&ts=1&hash=59c4eeb53e37e863220b75af0bc72505";

        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }
}
