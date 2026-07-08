package com.example.codebrawl.Controller.AuthController;

import com.example.codebrawl.ApiResponses.Response;
import com.example.codebrawl.Dtos.AuthDto.SignUpRequestDto;
import com.example.codebrawl.Dtos.AuthDto.SignUpResponseDto;
import com.example.codebrawl.Security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class SignupController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Response<SignUpResponseDto>> signup(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        log.info("Signup request received for username: {}", signUpRequestDto.getUsername());
        SignUpResponseDto responseDto = authService.signUp(signUpRequestDto);
        if (responseDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Response.failure("user is not created , please try again"));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Response.success("user created successfully", responseDto));
        }
    }
}

