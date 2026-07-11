package com.example.codebrawl.ExceptionHandling;

import lombok.Getter;

@Getter
public class InvalidCredentialsException extends RuntimeException {
    private final String ErrorCode;

    public InvalidCredentialsException(String ErrorCode, String message) {
        super(message);
        this.ErrorCode = ErrorCode;
    }
}
