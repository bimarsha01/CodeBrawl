package com.example.codebrawl.ApiResponses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private String message;
    private Boolean status;
    private T data;

    public static <T> Response<T> success(String message, T data) {
        return new Response<>(message, true, data);
    }

    public static <T> Response<T> failure(String message) {
        return new Response<>(message, false, null);
    }
}
