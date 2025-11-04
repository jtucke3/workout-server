package com.jtucke3.workoutapi.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.secret}") private String secret;
    @Value("${jwt.expMinutes:60}") private long expMinutes;

    public String generate(String subject, Map<String,Object> claims) {
        var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        var now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(Duration.ofMinutes(expMinutes))))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
