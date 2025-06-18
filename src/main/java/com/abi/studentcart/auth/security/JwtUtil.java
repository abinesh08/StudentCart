package com.abi.studentcart.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET;
    private final long EXPIRATION= 86400000;

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token){
        System.out.println(">>> Extracting username from token: " + token);
        return Jwts.parser().setSigningKey(SECRET.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            System.out.println(">>> Validating token: " + token);
            Jwts.parser()
                    .setSigningKey(SECRET.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(">>> Token validation error: " + e.getMessage());
            return false;
        }
    }
}
