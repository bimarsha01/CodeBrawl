package com.example.codebrawl.Entity.Model;


import com.example.codebrawl.Entity.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "users",
        indexes = {
                @Index(
                        name = "idx_users_email",
                        columnList = "email"
                ),
                @Index(
                        name = "idx_users_username",
                        columnList = "username"
                )
        }
)
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username" , nullable = false , length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String hashPassword;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role" , nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RoleEnum role = RoleEnum.ROLE_USER;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ProfileEntity profile;

    @Column(name = "contact", unique = true)
    private String contactNumber;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {

        return hashPassword;
    }

    @OneToMany(mappedBy = "user")
    private List<RefreshTokenEntity> refreshTokens = new ArrayList<>();
}
