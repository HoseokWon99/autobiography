package com.autobiography.users.service;

import com.autobiography.users.config.jwt.TokenProvider;
import com.autobiography.users.domain.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.Duration;

@AllArgsConstructor
@Service
@Transactional
public class TokenServiceImpl implements TokenService {
    private static final long ACCESS_TOKEN_DURATION = Duration.ofMinutes(20).toMillis();
    private static final long REFRESH_TOKEN_DURATION = Duration.ofDays(90).toMillis();

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UsersService usersService;

    @Override
    public Pair<String, String> createTokenPair(User user) {

        Pair<String, String> tokenPair = Pair.of(
                tokenProvider.generateToken(user, ACCESS_TOKEN_DURATION),
                tokenProvider.generateToken(user, REFRESH_TOKEN_DURATION)
        );

        refreshTokenService.createOrUpdate(user.getId(), tokenPair.getSecond());

        return tokenPair;
    }

    @Override
    public String createNewAccessToken(String refreshToken) {

        if(!tokenProvider.isValidToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByToken(refreshToken).getUserId();
        User user = usersService.findById(userId);

        return tokenProvider.generateToken(user, ACCESS_TOKEN_DURATION);
    }

    @Override
    public Long getAccessTokenDuration() {
        return ACCESS_TOKEN_DURATION;
    }

    @Override
    public Long getRefreshTokenDuration() {
        return REFRESH_TOKEN_DURATION;
    }
}
