package com.example.chat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class AccessCustomException extends RuntimeException{

    public AccessCustomException(String message){
        super(message);
    }
}
