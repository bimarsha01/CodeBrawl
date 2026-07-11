package com.example.codebrawl.ExceptionHandling;

import lombok.Getter;

@Getter
public class BadCredentialsException extends  RuntimeException{
    private final String ErrorCode;
    public BadCredentialsException(String ErrorCode , String message){
        super(message);
        this.ErrorCode = ErrorCode;
    }
}
