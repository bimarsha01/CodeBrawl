package com.example.codebrawl.Security;

import com.example.codebrawl.Entity.Model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

@Component
public class AuthUtil {
    @Value("${jwt.secretKey}")
    private String secretKey;


    public SecretKey getAccessTokenSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public SecretKey getRefreshTokenSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String getAccessToken(UserEntity user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId().toString())
                .claim("userRole", user.getRole().name())
                .signWith(getAccessTokenSecretKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + Duration.ofSeconds(40).toMillis()))
                .compact();
    }

    public String getRefreshToken(UserEntity user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId", user.getId())
                .signWith(getRefreshTokenSecretKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + Duration.ofMinutes(2).toMillis()))
                .compact();
    }

    public Claims validateRefreshToken(String refreshToken){
        return Jwts.parser()
                .verifyWith(getRefreshTokenSecretKey())
                .build()
                .parseSignedClaims(refreshToken)
                .getPayload();

//        it handles things like
//        jwt structure, signature along with the secret key and also expiration

    }
}
