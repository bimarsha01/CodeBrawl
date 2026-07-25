package com.example.codebrawl.Controller.AuthController;

import com.example.codebrawl.ApiResponses.Response;
import com.example.codebrawl.Dtos.AuthDto.LoginRequestDto;
import com.example.codebrawl.Dtos.AuthDto.LoginResponseDto;
import com.example.codebrawl.Dtos.AuthDto.LoginTokens;
import com.example.codebrawl.Security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(("/api/auth"))
public class LoginController {
    private final AuthService authService;
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
        System.out.println("Refresh token: " + refreshToken);

        return ResponseEntity.ok("Refresh token received");
    }
}
