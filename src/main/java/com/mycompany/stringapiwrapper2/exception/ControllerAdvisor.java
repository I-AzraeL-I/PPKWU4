package com.mycompany.stringapiwrapper2.exception;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(IncorrectMediaTypeException.class)
    public ResponseEntity<String> handleInvalidMediaType(IncorrectMediaTypeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return ResponseEntity.status(e.status()).body(e.getMessage());
    }
}
