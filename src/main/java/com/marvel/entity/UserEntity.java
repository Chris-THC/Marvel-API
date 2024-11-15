package com.marvel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false, unique = true)
    private Integer idUser;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "userPassword", nullable = false)
    private String userPassword;
}
