package com.example.codebrawl.ExceptionHandling;

import com.example.codebrawl.ApiResponses.Error;
import com.example.codebrawl.ApiResponses.Response;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                               HttpHeaders headers ,
                                                                               HttpStatusCode statusCode,
                                                                               WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> messageList = new ArrayList<>();
        for (FieldError error : fieldErrors) {
            String message = error.getField() + ": " + error.getDefaultMessage();
            messageList.add(message);
        }
        return new ResponseEntity<>(new Response<>("Data validation failed" , false , messageList) , headers , statusCode);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> notFoundException(NotFoundException ex){
        Error errors  = new Error(ex.getErrorCode() , Boolean.FALSE , ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Error> badCredentialsException(BadCredentialsException ex){
        Error errors = new Error(ex.getErrorCode(), Boolean.FALSE, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<Error> AlreadyExistException(AlreadyExistException ex){
        Error error = new Error(ex.getErrorCode(), Boolean.FALSE, ex.getMessage());
        return new ResponseEntity<>(error , BAD_REQUEST);

    }

    @ExceptionHandler(NotAvailableException.class)
    public ResponseEntity<Error> notAvailableException(NotAvailableException ex){
        Error errors = new Error(ex.getErrorCode(), Boolean.FALSE, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Error> invalidCredentialsException(InvalidCredentialsException ex){
        Error errors = new Error(ex.getErrorCode(), Boolean.FALSE, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Error> userAlreadyExistsException(UserAlreadyExistsException ex){
        Error errors = new Error(ex.getErrorCode(), Boolean.FALSE, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Error> expiredJwtException(ExpiredJwtException ex){
        Error errors = new Error("EXPIRED_JWT", Boolean.FALSE, ex.getMessage());
        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .maxAge(Duration.ZERO)
                .sameSite("Lax")
                .secure(false)
                .path("/")
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header(HttpHeaders.SET_COOKIE , cookie.toString()).body(errors);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex){
        Error error = new Error("INTERNAL_ERROR", Boolean.FALSE, "Something went wrong");
        return new ResponseEntity<>(error ,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
