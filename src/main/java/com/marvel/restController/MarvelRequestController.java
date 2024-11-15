package com.marvel.restController;

import com.marvel.service.MarvelRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/marvel")
public class MarvelRequestController {

    private final MarvelRequestService marvelRequestService;

    @GetMapping(path = "/characters")
    public Mono<ResponseEntity<String>> obtenerPersonajes() {
        return marvelRequestService.getAllCharacters()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
