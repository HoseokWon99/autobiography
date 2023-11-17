package com.autobiography.users.controller;

import com.autobiography.users.domain.dto.CheckCertificationRequest;
import com.autobiography.users.domain.dto.CheckCertificationResponse;
import com.autobiography.users.domain.dto.SendCertificationRequest;
import com.autobiography.users.domain.dto.SendCertificationResponse;
import com.autobiography.users.service.EMailCertificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/email")
public class EmailCertificationController {

    private final EMailCertificationService eMailCertificationService;

    @PostMapping()
    public ResponseEntity<SendCertificationResponse> send(
            @RequestBody
            @Valid
            SendCertificationRequest request
    ) {
        String key = eMailCertificationService.sendCode(request.email());

        return ResponseEntity.ok(
                new SendCertificationResponse(key)
        );
    }

    @PostMapping("/check")
    public ResponseEntity<CheckCertificationResponse> check(
            @RequestBody
            @Valid
            CheckCertificationRequest request
    ) {
        int result = eMailCertificationService.verifyCode(
                request.key(),
                request.code()
        );

        return ResponseEntity.ok(
                new CheckCertificationResponse(result)
        );
    }

}
