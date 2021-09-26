package com.example.chat.exceptions;

import com.example.chat.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class BaseControllerAdvice {


    @ExceptionHandler(AccessCustomException.class)
    public Object accessCustomException(AccessCustomException ex, WebRequest request){
        return response(HttpStatus.FORBIDDEN, ex, request);
    }
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public Object entityAlreadyExistsException(EntityAlreadyExistsException ex, WebRequest request){
        return response(HttpStatus.FOUND, ex, request);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public Object entityNotFoundException(EntityNotFoundException ex, WebRequest request){
        return response(HttpStatus.NOT_FOUND, ex, request);
    }
    @ExceptionHandler(AuthorizationCustomException.class)
    public Object authorizationCustomException(AuthorizationCustomException ex, WebRequest request){
        return response(HttpStatus.FORBIDDEN, ex, request);
    }

    private Object response(HttpStatus status, RuntimeException ex, WebRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.toString());
        body.put("message", ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(body, headers, status);
    }

}
