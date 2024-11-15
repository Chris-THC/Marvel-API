package com.marvel.restController;

import com.marvel.entity.MarvelRequest;
import com.marvel.service.MarvelRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/marvel")
public class MarvelRequestController {
    private final MarvelRequestService marvelRequestService;

    @GetMapping(path = "/api/characters")
    public Mono<ResponseEntity<String>> GetCharacters() {
        return marvelRequestService.getAllCharacters()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/time")
    public ResponseEntity<List<MarvelRequest>> getAllMarvelRequestsLogs() {
        final List<MarvelRequest> MarvelLogList = marvelRequestService.getAllMarvelRequestsLogs();
        return ResponseEntity.ok().body(MarvelLogList);
    }

    @GetMapping(path = "/api/characters/{idCharacters}")
    public Mono<ResponseEntity<String>> GetCharacterById(@PathVariable("idCharacters") final Integer idCharacters) throws Exception {
        return marvelRequestService.getCharacterById(idCharacters)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
