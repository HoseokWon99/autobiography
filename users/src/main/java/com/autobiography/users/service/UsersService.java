package com.autobiography.users.service;

import com.autobiography.users.domain.User;
import org.springframework.data.util.Pair;

public interface UsersService {

    void create(
            String email,
            String password,
            String name,
            String tel,
            String birth
    );

    User findById(Long id);

    User findByEmail(String email);

    void updateEmail(String newEmail);

    void updatePassword(String newPassword);

}
