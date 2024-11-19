package io.github.qaqchat.logindemo.util;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${qaqchat.jwt.secret-key}")
    private String secretKey;

    @Value("${qaqchat.jwt.expiration-time}")
    private long expirationTime;

    private SecretKey key; @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public Jwt<?, ?> parseJwt(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parse(token);
    }
}

