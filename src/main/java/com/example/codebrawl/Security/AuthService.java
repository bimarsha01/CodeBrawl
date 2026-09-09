package com.example.codebrawl.Security;

import com.example.codebrawl.Dtos.AuthDto.LoginRequestDto;
import com.example.codebrawl.Dtos.AuthDto.LoginTokens;
import com.example.codebrawl.Dtos.AuthDto.SignUpRequestDto;
import com.example.codebrawl.Dtos.AuthDto.SignUpResponseDto;
import com.example.codebrawl.Entity.Model.RefreshTokenEntity;
import com.example.codebrawl.Entity.Model.UserEntity;
import com.example.codebrawl.Entity.RoleEnum;
import com.example.codebrawl.ExceptionHandling.UserAlreadyExistsException;
import com.example.codebrawl.Mapper.AuthMapper;
import com.example.codebrawl.Repo.RefreshTokenEntityRepo;
import com.example.codebrawl.Repo.UserRepo;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;
    private final RefreshTokenEntityRepo refreshTokenEntityRepo;

    public LoginTokens login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        UserEntity user = customUserDetails.getUser();

        log.info("Authenticated user: {}", user.getUsername());

        String accessToken = authUtil.getAccessToken(user);
        String refreshToken = authUtil.getRefreshToken(user);

        LocalDateTime expiresAt = LocalDateTime.now().plusDays(7);

        saveRefreshToken(user, refreshToken, expiresAt);

        return new LoginTokens(user.getId(), accessToken , refreshToken);
    }

    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto dto) {

        if (userRepo.existsByUsername(dto.getUsername())) {
                throw new UserAlreadyExistsException("USER_ALREADY_EXISTS", "User with username " + dto.getUsername() + " already exists");
        }
        if(userRepo.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("USER_ALREADY_EXISTS", "User with email " + dto.getEmail() + " already exists");
        }
        if(userRepo.existsByContactNumber(dto.getContactNumber())){
            throw new UserAlreadyExistsException("USER_ALREADY_EXIST" , "user with contact number " + dto.getContactNumber() + " already exists");

        }

        log.info("Creating new user '{}'", dto.getUsername());
        UserEntity user = authMapper.toEntity(dto);

        user.setHashPassword(
                passwordEncoder.encode(dto.getPassword())
        );
        user.setRole(RoleEnum.ROLE_USER);

        UserEntity savedUser = userRepo.save(user);

        log.info("User '{}' created successfully", savedUser.getUsername());

        return authMapper.toDto(savedUser);
    }


    public String refreshAccessToken(String refreshToken) {

        Claims claims = authUtil.validateRefreshToken(refreshToken);

        RefreshTokenEntity entity = refreshTokenEntityRepo.findByRefreshToken(refreshToken);

        if(entity.isRevoked()){
            throw new AccessDeniedException("SORRY ! YOU HAVE TO LOGIN AGAIN");
        }
        if (entity.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new AccessDeniedException("SORRY YOU HAVE TO LOGIN AGAIN");
        }

        Long userId = claims.get("userId", Long.class);

        UserEntity user = userRepo.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User with id" + userId +"is not found"));

        return authUtil.getAccessToken(user);
    }

    private void saveRefreshToken(UserEntity user,
                                  String refreshToken,
                                  LocalDateTime expiresAt) {

        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder()
                .refreshToken(refreshToken)
                .user(user)
                .createdAt(LocalDateTime.now())
                .expiresAt(expiresAt)
                .build();

        refreshTokenEntityRepo.save(refreshTokenEntity);

        log.info("Refresh token saved for user '{}'", user.getUsername());
    }
}
