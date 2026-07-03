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
@Table(name = "submissions")
public class SubmissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "battle_id", nullable = false)
    private BattleEntity battle;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Lob
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "language", nullable = false)
    private String language;

    @Enumerated(EnumType.STRING)
    private Verdict verdict;

    private LocalDateTime submittedAt;
}
