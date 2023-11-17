package com.autobiography.users.service;

import com.autobiography.users.domain.User;
import org.springframework.data.util.Pair;

public interface TokenService {
    Pair<String, String> createTokenPair(User user);
    String createNewAccessToken(String refreshToken);
    Long getAccessTokenDuration();
    Long getRefreshTokenDuration();
}
