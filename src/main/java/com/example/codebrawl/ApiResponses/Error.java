package com.example.codebrawl.ApiResponses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Error<T> {
    private String error;
    private Boolean status;
    private T data;

}
