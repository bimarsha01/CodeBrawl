package com.example.codebrawl.Service.ProfileService;

import com.example.codebrawl.Dtos.ProfileDtos.ProfileRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class profileService {

    public boolean createProfile(ProfileRequestDto profileRequestDto){

        return true;
    }
}
