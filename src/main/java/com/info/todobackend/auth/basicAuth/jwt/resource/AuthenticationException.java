package com.info.todobackend.auth.basicAuth.jwt.resource;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
