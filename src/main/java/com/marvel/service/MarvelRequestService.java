package com.marvel.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.marvel.entity.MarvelRequest;
import com.marvel.repository.MarvelRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarvelRequestService {
    private final MarvelRequestRepository marvelRequestRepository;
    private final WebClient webClient;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private void TimeAndServiceLog(String serviceName) {
        MarvelRequest registerTime = MarvelRequest.builder()
                .timestamp(LocalDateTime.now())
                .service(serviceName)
                .build();
        marvelRequestRepository.save(registerTime);
    }

    public Mono<String> getAllCharacters() {
        String url = "characters?apikey=bf300db1f72ad8ed3885f148bddfe897&ts=1&hash=59c4eeb53e37e863220b75af0bc72505";
        TimeAndServiceLog("characters");

        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(jsonString -> gson.toJson(gson.fromJson(jsonString, Object.class)));
    }

    public List<MarvelRequest> getAllMarvelRequestsLogs() {
        return marvelRequestRepository.findAll();
    }

}
