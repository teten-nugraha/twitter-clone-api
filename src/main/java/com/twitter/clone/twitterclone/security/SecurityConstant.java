package com.twitter.clone.twitterclone.security;

public class SecurityConstant {

    public static final String SIGN_UP_URLS     = "/api/users/**";
    public static final String H2_URL           = "/h2-console/**";
    public static final String SECRET           = "SecretForJWT";
    public static final String TOKEN_PREFIX     = "Bearer ";
    public static final String HEADER_STRING    = "Authorization";
    public static final long EXPIRE_TIME      = 3600000;
}
