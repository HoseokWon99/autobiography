package com.autobiography.users.service;

import com.autobiography.users.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepos;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (!usersRepos.existsByEmail(username))
            throw new UsernameNotFoundException("Unexpected Email");

        return new org.springframework.security.core.userdetails.User(
                username, "",
                Collections.singleton(new SimpleGrantedAuthority("OWNER"))
        );
    }
}
