package com.autobiography.users.controller;

import com.autobiography.users.domain.dto.LoginRequest;
import com.autobiography.users.domain.dto.LoginResponse;
import com.autobiography.users.domain.dto.RenewAccessTokenRequest;
import com.autobiography.users.domain.dto.RenewAccessTokenResponse;
import com.autobiography.users.service.UserAuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody
            @Valid
            LoginRequest request
    ) {
        Pair<String, String> tokenPair = userAuthService.login(
                request.email(),
                request.password()
        );

        return ResponseEntity.ok(
                new LoginResponse(
                        tokenPair.getFirst(),
                        tokenPair.getSecond()
                )
        );
    }

    @PostMapping("/renew")
    public ResponseEntity<RenewAccessTokenResponse> renew(
            @RequestBody
            @Valid
            RenewAccessTokenRequest request
    ) {
        String accessToken = userAuthService.renew(
                request.refreshToken()
        );

        return ResponseEntity.ok(
                new RenewAccessTokenResponse(accessToken)
        );
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        userAuthService.logout();
        return ResponseEntity.ok().build();
    }

}
