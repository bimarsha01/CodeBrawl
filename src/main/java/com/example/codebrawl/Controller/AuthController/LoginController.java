package com.example.codebrawl.Controller.AuthController;

import com.example.codebrawl.ApiResponses.Response;
import com.example.codebrawl.Dtos.AuthDto.LoginRequestDto;
import com.example.codebrawl.Dtos.AuthDto.LoginResponseDto;
import com.example.codebrawl.Dtos.AuthDto.LoginTokens;
import com.example.codebrawl.Security.AuthService;
import com.example.codebrawl.Security.AuthUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(("/api/auth"))
public class LoginController {
    private final AuthService authService;
    private final AuthUtil authUtil;
    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        log.info("Login request received for username: {}", loginRequestDto.getUsername());
        LoginTokens responseDto = authService.login(loginRequestDto);
        if (responseDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Response.failure("Invalid credentials"));
        }
    else {
            String refreshToken = responseDto.getRefreshToken();
            ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                    .httpOnly(true)
                    .secure(false)
                    .sameSite("Lax")
                    .path("/")
                    .maxAge(Duration.ofDays(7))
                    .build();
            LoginResponseDto tokens = new LoginResponseDto(responseDto.getId(), responseDto.getAccessToken());
            return ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(Response.success("Login successful", tokens));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(
            @CookieValue("refreshToken") String refreshToken
    ) {
        try {
            String newAccessToken = authService.refreshAccessToken(refreshToken);
            return ResponseEntity.ok(newAccessToken);

//            now since the claim is done now what we do is to get the user from it via claims

        }catch (ExpiredJwtException e) {
            ResponseCookie cookie = ResponseCookie.from("refreshToken" , "")
                    .httpOnly(true)
                    .secure(false)
                    .sameSite("Lax")
                    .path("/")
                    .maxAge(0)
                    .build();

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header(HttpHeaders.SET_COOKIE , cookie.toString())
                    .body("Session expired. please login again");
        }
    }
}
