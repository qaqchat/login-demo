package io.github.qaqchat.logindemo.util;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

import java.security.Key;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${qaqchat.jwt.secret-key}")
    private String secretKey;

    @Value("${qaqchat.jwt.expiration-time}")
    private long expirationTime;

    public String generateToken(String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }
}

