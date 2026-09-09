package com.example.codebrawl.Dtos.AuthDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
//    @NotBlank(message = "Username is Required")
//    @Size(min =3 , max = 20 , message = "Username must be between 3 and 20 characters")
//    @Pattern(
//            regexp = "^[a-zA-Z0-9_]+$",
//            message = "Username can only contain letters, numbers, and underscores"
//    )
//    private String username;

    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid email format")
    private String email;


    @NotBlank(message = "Password is Required")
    @Size(min =6 , max = 100 , message = "Password must be between 6 and 100 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
    private String password;

//    @NotBlank(message = "Contact is required")
//    @Pattern(
//            regexp = "^(98|97)\\d{8}$",
//            message = "Contact must be a valid phone number starting with 98 or 97"
//    )
//    private String contactNumber;


}
