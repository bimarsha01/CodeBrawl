package com.example.codebrawl.Controller.AuthController;


import com.example.codebrawl.Entity.Model.RefreshTokenEntity;
import com.example.codebrawl.Repo.RefreshTokenEntityRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LogoutController {

    private final RefreshTokenEntityRepo refreshTokenEntityRepo;

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @CookieValue("refreshToken") String refreshToken
    ) {

        RefreshTokenEntity entity =
                refreshTokenEntityRepo.findByToken(refreshToken);

        if (entity != null) {
            entity.setRevoked(true);
            refreshTokenEntityRepo.save(entity);
        }

        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Logout Successfully");
    }
}