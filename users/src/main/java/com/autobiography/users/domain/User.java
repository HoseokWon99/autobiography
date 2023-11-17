package com.autobiography.users.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "tel", nullable = false)
    private String tel;

    @Column(name = "birth", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private String birth;

    @Builder
    public User(
            String email,
            String password,
            String name,
            String tel,
            String birth
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.birth = birth;
    }

}
