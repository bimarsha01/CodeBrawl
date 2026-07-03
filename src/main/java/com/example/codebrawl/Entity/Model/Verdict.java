package com.example.codebrawl.Entity.Model;

import lombok.Getter;

@Getter
public enum Verdict {
    PENDING,
    ACCEPTED,
    WRONG_ANSWER,
    RUNTIME_ERROR,
    COMPILATION_ERROR
}
