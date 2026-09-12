package com.example.codebrawl.Dtos.ProfileDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class ProfileRequestDto {

    @NotBlank(message = "Username is Required")
    @Size(min =3 , max = 20 , message = "Username must be between 3 and 20 characters")
    @Pattern(
            regexp = "^[a-zA-Z0-9_]+$",
            message = "Username can only contain letters, numbers, and underscores"
    )
    private String username;

    @Pattern(
            regexp = "^[a-zA-Z0-9-]+$",
            message = "Invalid GitHub username"
    )
    private String githubUsername;

    @Pattern(
            regexp = "^[a-zA-Z0-9-]+$",
            message = "Invalid LinkedIn username"
    )
    private String linkedInUsername;

    private String bio;

    private String avatarUrl;

    private String country;

}
