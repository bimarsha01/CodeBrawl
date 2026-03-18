package com.example.codebrawl.User.Model;


import com.example.codebrawl.User.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(name = "password", nullable = false)
    private String hashPassword;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role" , nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RoleEnum role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ProfileEntity profile;


}
