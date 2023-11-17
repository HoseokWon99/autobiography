package com.autobiography.users.domain.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken
) { }
