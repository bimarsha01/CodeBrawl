package com.example.codebrawl.ExceptionHandling;

import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException{

    private final String ErrorCode;

    public UnauthorizedException(String ErrorCode , String message){
        super(message);
        this.ErrorCode = ErrorCode;
    }
}
