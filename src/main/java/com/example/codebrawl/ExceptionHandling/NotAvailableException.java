package com.example.codebrawl.ExceptionHandling;

import lombok.Getter;

@Getter
public class NotAvailableException extends RuntimeException {
    private final String ErrorCode;

    public NotAvailableException(String ErrorCode , String message){
        super(message);
        this.ErrorCode = ErrorCode;
    }
}
