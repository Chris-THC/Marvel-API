package com.marvel.dto;

import com.marvel.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import org.jsondoc.core.annotation.ApiObjectField;

@Data
@Builder
public class UserDTO {
    @ApiObjectField(name = "idUser", description = "User's ID")
    private int idUser;

    @ApiObjectField(name = "userName", description = "User's name")
    private String userName;


    public static UserDTO build(final UserEntity user) {
        return UserDTO.builder()
                .idUser(user.getIdUser())
                .userName(user.getUsername())
                .build();
    }
}
