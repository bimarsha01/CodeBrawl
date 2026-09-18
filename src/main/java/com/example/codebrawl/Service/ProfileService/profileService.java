package com.example.codebrawl.Service.ProfileService;

import com.example.codebrawl.Dtos.ProfileDtos.ProfileRequestDto;
import com.example.codebrawl.Entity.Model.ProfileEntity;
import com.example.codebrawl.Entity.Model.UserEntity;
import com.example.codebrawl.ExceptionHandling.UnauthorizedException;
import com.example.codebrawl.ExceptionHandling.UserAlreadyExistsException;
import com.example.codebrawl.Mapper.ProfileMapper;
import com.example.codebrawl.Repo.ProfileRepo;
import com.example.codebrawl.Repo.UserRepo;
import com.example.codebrawl.Security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class profileService {

    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;
    private final ProfileMapper profileMapper;

    @Transactional
    public void createProfile(ProfileRequestDto requestDto) {

        if (userRepo.existsByUsername(requestDto.getUsername())) {
            throw new UserAlreadyExistsException(
                    "USER_ALREADY_EXISTS",
                    "Username is already taken"
            );
        }

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                !(authentication.getPrincipal() instanceof CustomUserDetails principal)) {

            throw new UnauthorizedException(
                    "UNAUTHORIZED",
                    "User is not authenticated"
            );
        }

        UserEntity user = principal.getUser();

        if (profileRepo.existsByUser(user)) {
            throw new UserAlreadyExistsException(
                    "USER_ALREADY_EXISTS",
                    "Profile already exists"
            );
        }

        ProfileEntity profile =
                profileMapper.toEntity(requestDto);

        user.setUsername(requestDto.getUsername());

        profile.setUser(user);

        profileRepo.save(profile);
    }
}
