package com.autobiography.users.service;

import org.springframework.data.util.Pair;

public interface UserAuthService {
    Pair<String, String> login(String email, String password);
    String renew(String refreshToken);
    void logout();
}
