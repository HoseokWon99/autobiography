package com.autobiography.users.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SignupRequest(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp ="^(?!(([A-Za-z]+)|([~!@#$%^&*()_+=]+)|([0-9]+))$)[A-Za-z\\d~!@#$%^&*()_+=]{8,16}$" )
        String password,
        @NotBlank
        @Pattern(regexp = "^[가-힣]+$")
        String name,
        @NotBlank
        @Pattern(regexp = "^010(\\d{4})(\\d{4})$")
        String tel,
        @NotBlank
        @Pattern(regexp = "^([0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))$")
        String birth
) { }
