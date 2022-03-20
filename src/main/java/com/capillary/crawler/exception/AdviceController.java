package com.capillary.crawler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(UrlReadingException.class)
    public ResponseEntity<String> UrlReadingException( UrlReadingException urlReadingException){
        return new ResponseEntity<>(urlReadingException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
