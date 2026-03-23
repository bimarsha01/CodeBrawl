package com.example.codebrawl.User.Model;


import com.example.codebrawl.DifficultyEnum;
import com.example.codebrawl.LanguageEnum;
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
@Table(name = "problems")
public class ProblemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "problem_name" , nullable = false)
    private String name;


    @Column(name = "problem_difficulty" , nullable = false)
    private DifficultyEnum difficulty;


    @Column(name = "problem_language" , nullable = false)
    private LanguageEnum language;


    @Column(name = "problem_constraints" , nullable = false)
    private String constraints;


    @Column(name = "problem_complexities" , nullable = false)
    private String complexities;


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
