package com.example.codebrawl.Dtos.AuthDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    private String username;
    private String password;
    private String contacts;
    private String email;
}
