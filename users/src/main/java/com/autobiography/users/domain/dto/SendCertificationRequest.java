package com.autobiography.users.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SendCertificationRequest(
        @NotBlank
        @Email
        String email
) { }
