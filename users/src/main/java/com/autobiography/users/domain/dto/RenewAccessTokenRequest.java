package com.autobiography.users.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record RenewAccessTokenRequest(
        @NotBlank
        String refreshToken
) { }
