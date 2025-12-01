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

    /**
     * Validates a JWT token and returns the user email if valid.
     * @param token The JWT token to validate
     * @return The user email from the token subject
     * @throws JwtException if the token is invalid, expired, or malformed
     */
    public String validateToken(String token) {
        try {
            var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            
            return claims.getSubject(); // This should be the user email
        } catch (ExpiredJwtException e) {
            throw new JwtException("Token has expired", e);
        } catch (UnsupportedJwtException e) {
            throw new JwtException("Token is not supported", e);
        } catch (MalformedJwtException e) {
            throw new JwtException("Token is malformed", e);
        } catch (SignatureException e) {
            throw new JwtException("Token signature validation failed", e);
        } catch (IllegalArgumentException e) {
            throw new JwtException("Token is invalid", e);
        }
    }

    /**
     * Extracts the user email from a valid JWT token without full validation.
     * Use validateToken for security-critical operations.
     * @param token The JWT token
     * @return The user email from the token subject
     */
    public String extractEmail(String token) {
        var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        
        return claims.getSubject();
    }
}
