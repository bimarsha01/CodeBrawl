package com.example.codebrawl.User.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity

@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_wins", nullable = false)
    private int userWins;

    @Column(name = "user_loses", nullable = false)
    private int userLosses;

    @Column(name = "user_country", nullable = false)
    private String country;

    @Column(name = "xp")
    private String xp;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private UserEntity user;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
