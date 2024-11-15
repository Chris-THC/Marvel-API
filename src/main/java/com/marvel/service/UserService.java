package com.marvel.service;

import com.marvel.dto.UserDTO;
import com.marvel.entity.UserEntity;
import com.marvel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {

        final List<UserEntity> users = userRepository.findAll();
        return users
                .stream()
                .map(UserDTO::build)
                .sorted(Comparator.comparing(UserDTO::getIdUser).reversed())
                .toList();
    }

    public UserDTO getUserByName(final String userName) {
        final UserEntity user = userRepository.findByUserName(userName);
        return UserDTO.build(user);

    }

}
