package com.test.user.article.configuration.security;

public interface SecurityConstants {
    String SECRET = "SecretKeyToGenJWTs";
    long EXPIRATION_TIME = 864_000_000;
    String TOKEN_PREFIX = "Bearer";
    String HEADER_STRING = "Authorization";
    String ALLOW_TOKEN_ACCESS_HEADER = "Access-Control-Expose-Headers";
}
