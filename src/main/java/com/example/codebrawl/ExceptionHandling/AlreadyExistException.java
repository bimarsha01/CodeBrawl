package com.example.codebrawl.ExceptionHandling;

import lombok.Getter;

@Getter
public class AlreadyExistException extends RuntimeException {
    private final String ErrorCode;
    public AlreadyExistException(String errorCode, String message) {
        super(message);
        this.ErrorCode = errorCode;
    }
}
