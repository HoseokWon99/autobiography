package com.autobiography.users.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CheckCertificationRequest(
    @NotBlank
    String key,
    @NotBlank
    @Size(min = 6, max = 6)
    String code
) { }
