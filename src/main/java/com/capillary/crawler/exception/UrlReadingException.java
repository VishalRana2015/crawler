package com.capillary.crawler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UrlReadingException extends Exception{

    public UrlReadingException(String msg){
        super(msg);
    }
}
