package com.mycompany.stringapiwrapper2.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(IncorrectMediaTypeException.class)
    public ResponseEntity<String> handleInvalidMediaType(IncorrectMediaTypeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
