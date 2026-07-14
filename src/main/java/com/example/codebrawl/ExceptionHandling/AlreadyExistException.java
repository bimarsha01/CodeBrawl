package com.example.codebrawl.ExceptionHandling;

public class AlreadyExistException extends RuntimeException {
  public AlreadyExistException(String message) {
    super(message);
  }
}
