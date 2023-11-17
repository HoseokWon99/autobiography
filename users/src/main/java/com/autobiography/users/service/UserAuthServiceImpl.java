package com.autobiography.users.service;

import com.autobiography.users.domain.User;
import com.autobiography.users.utils.cache.KeyValueRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Transactional
public class UserAuthServiceImpl implements UserAuthService {

    private final UsersService usersService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final KeyValueRepository<String, Object> blackList;

    @Override
    public Pair<String, String> login(String email, String password) {
        User user = usersService.findByEmail(email);

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new IllegalArgumentException();


        if (blackList.hasKey(email))
            blackList.deleteData(email);

        return tokenService.createTokenPair(user);
    }

    @Override
    public String renew(String refreshToken) {
        return tokenService.createNewAccessToken(refreshToken);
    }

    @Override
    public void logout() {

        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();

        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        String accessToken = (String) authentication.getCredentials();

        if(!blackList.hasKey(email))
            blackList.setData(email, accessToken, tokenService.getAccessTokenDuration());
    }
}
