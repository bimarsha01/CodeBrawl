package com.example.codebrawl.Mapper;

import com.example.codebrawl.Dtos.ProfileDtos.ProfileRequestDto;
import com.example.codebrawl.Entity.Model.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "username" , ignore = true)
    ProfileEntity toEntity(ProfileRequestDto dto);
}
