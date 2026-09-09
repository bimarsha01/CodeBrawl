package com.example.codebrawl.Controller.ProfileController;


import com.example.codebrawl.ApiResponses.Response;
import com.example.codebrawl.Dtos.AuthDto.LoginResponseDto;
import com.example.codebrawl.Dtos.ProfileDtos.ProfileRequestDto;
import com.example.codebrawl.Service.ProfileService.profileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class profileController {

    private final profileService profileService;

    @PostMapping("/createProfile")
    public ResponseEntity<Response<LoginResponseDto>> createProfile(@Valid @RequestBody ProfileRequestDto profileRequestDto){


        boolean created = profileService.createProfile(profileRequestDto);

    }
}
