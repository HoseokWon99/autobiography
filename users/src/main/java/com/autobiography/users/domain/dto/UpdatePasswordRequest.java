package com.autobiography.users.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdatePasswordRequest(
        @NotBlank
        @Pattern(regexp ="^(?!(([A-Za-z]+)|([~!@#$%^&*()_+=]+)|([0-9]+))$)[A-Za-z\\d~!@#$%^&*()_+=]{8,16}$" )
        String newPassword
) { }
