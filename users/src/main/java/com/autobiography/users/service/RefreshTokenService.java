package com.autobiography.users.service;

import com.autobiography.users.domain.RefreshToken;

public interface RefreshTokenService {
    void createOrUpdate(Long userId, String token);
    RefreshToken findByUserId(Long userId);
    RefreshToken findByToken(String refreshToken);
}
