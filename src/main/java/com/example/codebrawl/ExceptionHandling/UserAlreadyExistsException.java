package com.example.codebrawl.ExceptionHandling;

import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {
    private final String ErrorCode;
    public UserAlreadyExistsException(String errorCode, String message) {
        super(message);
        this.ErrorCode = errorCode;
    }
}
