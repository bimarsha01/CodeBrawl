package com.example.codebrawl.Service.ProfileService;

import com.example.codebrawl.Dtos.ProfileDtos.ProfileRequestDto;
import com.example.codebrawl.Entity.Model.ProfileEntity;
import com.example.codebrawl.Entity.Model.UserEntity;
import com.example.codebrawl.ExceptionHandling.UserAlreadyExistsException;
import com.example.codebrawl.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class profileService {

    private final UserRepo userRepo;

    public void createProfile(ProfileRequestDto requestDto){

        if(userRepo.existsByUsername(requestDto.getUsername())){
            throw new UserAlreadyExistsException("USER_ALREADY_EXISTS", "Username is already taken");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity user = (UserEntity) authentication.getPrincipal();


        ProfileEntity profile = ProfileEntity.builder()
                .bio(requestDto.getBio())
                .country(requestDto.getCountry())
                .avatarUrl(requestDto.getAvatarUrl())
                .githubProfile(requestDto.getGithubUsername())
                .linkedinProfile(requestDto.getLinkedInUsername())
                .user(user)
                .build();

        user.setUsername(requestDto.getUsername());


//        return true;
    }
}
