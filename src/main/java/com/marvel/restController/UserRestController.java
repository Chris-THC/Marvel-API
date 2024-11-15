package com.marvel.restController;

import com.marvel.entity.UserEntity;
import com.marvel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserRestController {
    private final UserService userService;

    @GetMapping(path = "")
    public ResponseEntity<List<UserEntity>> getAllMarvelRequestsLogs() {
        final List<UserEntity> MarvelLogList = userService.getAllUsers();
        return ResponseEntity.ok().body(MarvelLogList);
    }

}
