package com.autobiography.users.config.jwt;

import com.autobiography.users.domain.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProps;

    public String generateToken(User user, long expiredAfter) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiredAfter);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProps.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS256, jwtProps.getSecretKey())
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
           parseJws(token);
           return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("OWNER"));

        return new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(claims.getSubject
                (), "", authorities), token, authorities
        );
    }

    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    private Jws<Claims> parseJws(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProps.getSecretKey())
                .parseClaimsJws(token);
    }

    private Claims getClaims(String token) {
        return parseJws(token).getBody();
    }

}
