package com.lpdm.msstore.exception.handler;

import com.lpdm.msstore.exception.StoreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StoreNotFoundException.class)
    public String storeNotFoundHandler(StoreNotFoundException e){
        return e.getMessage();
    }
}
