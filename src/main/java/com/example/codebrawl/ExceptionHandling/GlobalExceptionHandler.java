package com.example.codebrawl.ExceptionHandling;

import com.example.codebrawl.ApiResponses.Error;
import com.example.codebrawl.ApiResponses.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(NotAvailableException.class)
    public ResponseEntity<Error> notAvailableException(NotAvailableException ex){
        Error errors = new Error(ex.getErrorCode(), Boolean.FALSE, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Error> invalidCredentialsException(InvalidCredentialsException ex){
        Error errors = new Error(ex.getErrorCode(), Boolean.FALSE, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);

    }@ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Error> userAlreadyExistsException(UserAlreadyExistsException ex){
        Error errors = new Error(ex.getErrorCode(), Boolean.FALSE, ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex){
        Error error = new Error("INTERNAL_ERROR", Boolean.FALSE, "Something went wrong");
        return new ResponseEntity<>(error ,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
