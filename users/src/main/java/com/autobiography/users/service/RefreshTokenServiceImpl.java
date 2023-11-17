package com.autobiography.users.service;

import com.autobiography.users.domain.RefreshToken;
import com.autobiography.users.repository.RefreshTokensRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{

    private final RefreshTokensRepository refreshTokensRepos;

    @Override
    public void createOrUpdate(Long userId, String token) {
        Optional<RefreshToken> opt = refreshTokensRepos.findByUserId(userId);

        if (opt.isEmpty()) {

            refreshTokensRepos.save(
                    RefreshToken.builder()
                            .userId(userId)
                            .token(token)
                            .build()
            );

        }
        else {
            opt.get().setToken(token);
        }

    }

    @Override
    public RefreshToken findByUserId(Long userId) {
        return refreshTokensRepos.findByUserId(userId)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public RefreshToken findByToken(String refreshToken) {
        return refreshTokensRepos.findByToken(refreshToken)
                .orElseThrow(IllegalArgumentException::new);
    }
}
