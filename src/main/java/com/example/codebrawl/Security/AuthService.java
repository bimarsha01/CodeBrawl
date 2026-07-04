package com.example.codebrawl.Security;

import com.example.codebrawl.Dtos.AuthDto.SignUpRequestDto;
import com.example.codebrawl.Dtos.AuthDto.SignUpResponseDto;
import com.example.codebrawl.Dtos.AuthDto.LoginRequestDto;
import com.example.codebrawl.Dtos.AuthDto.LoginResponseDto;
import com.example.codebrawl.Entity.Model.UserEntity;
import com.example.codebrawl.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        UserEntity user = (UserEntity) authentication.getPrincipal();
        assert user != null;
        String token = authUtil.getAccessToken(user);
        return new LoginResponseDto(token , user.getId());
    }

    public SignUpResponseDto signUp(SignUpRequestDto dto) {

        UserEntity existingUser = userRepo.findByUsername(dto.getUsername())
                .orElse(null);

        if (existingUser != null) {
            throw new RuntimeException("User already exists");
        }

        UserEntity user = userRepo.save(
                UserEntity.builder()
                        .username(dto.getUsername())
                        .hashPassword(passwordEncoder.encode(dto.getPassword()))
                        .build()
        );

        return new SignUpResponseDto(user.getUsername(), user.getId());
    }

}
