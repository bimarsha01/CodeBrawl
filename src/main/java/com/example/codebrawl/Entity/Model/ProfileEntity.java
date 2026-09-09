package com.example.codebrawl.Entity.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

//    @Column(name = "user_name", nullable = false)
//    private String userName;

    @Column(name = "user_country", nullable = false)
    private String country;

    @Column(name = "user_bio")
    private String bio;

    @Column(name = "user_github_profile" , unique = true)
    private String githubProfile;

    @Column(name = "user_linkedin_profile" , unique = true)
    private String linkedinProfile;

    @Column(name = "user_avatarUrl")
    private String avatarUrl;

    @OneToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private UserEntity user;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}

