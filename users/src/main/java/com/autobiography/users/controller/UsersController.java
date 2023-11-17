package com.autobiography.users.controller;

import com.autobiography.users.domain.dto.SignupRequest;
import com.autobiography.users.domain.dto.UpdateEmailRequest;
import com.autobiography.users.domain.dto.UpdatePasswordRequest;
import com.autobiography.users.service.EMailService;
import com.autobiography.users.service.UsersService;
import com.autobiography.users.utils.SecurityContextUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@AllArgsConstructor
@Controller
public class UsersController {

    private final UsersService usersService;
    private final EMailService eMailService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(
            @RequestBody
            @Valid
            SignupRequest request
    ) {
        usersService.create(
                request.email(),
                request.password(),
                request.name(),
                request.tel(),
                request.birth()
        );

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/update/email", method = RequestMethod.POST)
    public ResponseEntity<?> updateEmail(
            @RequestBody
            @Valid
            UpdateEmailRequest request
    ) {
        usersService.updateEmail(request.newEmail());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    public ResponseEntity<?> updatePassword(
            @RequestBody
            @Valid
            UpdatePasswordRequest request
    ) {
        usersService.updateEmail(request.newPassword());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/find/password", method = RequestMethod.GET)
    public ResponseEntity<?> findPassword() {
        String email = SecurityContextUtil.getUsernameFromSecurityContext();
        String password = usersService.findByEmail(email).getPassword();
        eMailService.sendEMail(email, "자서전 비밀번호", password);
        return ResponseEntity.ok().build();
    }
}
