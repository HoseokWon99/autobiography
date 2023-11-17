package com.autobiography.users.service;

import com.autobiography.users.domain.User;
import com.autobiography.users.utils.SecurityContextUtil;
import com.autobiography.users.utils.exception.UserAlreadyExistsException;
import com.autobiography.users.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepos;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void create(
            String email,
            String password,
            String name,
            String tel,
            String birth
    ) {
        if (usersRepos.existsByEmail(email))
            throw new UserAlreadyExistsException();

        usersRepos.save(
                User.builder()
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .name(name)
                        .tel(tel)
                        .birth(birth)
                        .build()
        );
    }

    @Override
    public User findById(Long id) {
        return usersRepos.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public User findByEmail(String email) {
        return usersRepos.findByEmail(email)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void updateEmail(String newEmail) {

        User user = findByEmail(
                SecurityContextUtil.getUsernameFromSecurityContext()
        );

        user.setEmail(newEmail);
    }

    @Override
    public void updatePassword(String newPassword) {

        User user = findByEmail(
                SecurityContextUtil.getUsernameFromSecurityContext()
        );

        user.setPassword(newPassword);
    }

}



