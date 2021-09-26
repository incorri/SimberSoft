package com.example.chat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class AuthorizationCustomException extends RuntimeException{
    public AuthorizationCustomException(String message) {
        super(message);
    }
}