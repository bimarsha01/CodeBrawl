package com.example.codebrawl.ApiResponses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Error<T> {
    private String error;
    private Boolean status;
    private T data;

}
