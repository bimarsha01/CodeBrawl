package com.example.codebrawl.ExceptionHandling;

import com.example.codebrawl.ApiResponses.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        return new ResponseEntity<>(new Response("Data validation failed" , false , messageList) , headers , statusCode);
    }

}
