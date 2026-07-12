package com.example.codebrawl.Constants;

public final class JwtConstants {

    private JwtConstants(){}
    public static final long ACCESS_TOKEN_EXPIRATION = 15*60*1000L;
    public static final long REFRESH_TOKEN_EXPIRATION = 7*24*60*60*1000L;
}
