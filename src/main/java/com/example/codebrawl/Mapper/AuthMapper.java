package com.example.codebrawl.Mapper;

import com.example.codebrawl.Dtos.AuthDto.SignUpRequestDto;
import com.example.codebrawl.Dtos.AuthDto.SignUpResponseDto;
import com.example.codebrawl.Entity.Model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hashPassword", ignore = true)
    UserEntity toEntity(SignUpRequestDto signUpRequestDto);

    SignUpResponseDto toDto(UserEntity user);
}
