package com.example.codebrawl.Controller.AuthController;

import com.example.codebrawl.ApiResponses.Response;
import com.example.codebrawl.Dtos.AuthDto.LoginRequestDto;
import com.example.codebrawl.Dtos.AuthDto.LoginResponseDto;
import com.example.codebrawl.Security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(("/api/auth"))
public class LoginController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        log.info("Login request received for username: {}", loginRequestDto.getUsername());
        LoginResponseDto responseDto = authService.login(loginRequestDto);
        if (responseDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Response.failure("Invalid credentials"));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Response.success("Login successful", responseDto));
        }
    }
}
