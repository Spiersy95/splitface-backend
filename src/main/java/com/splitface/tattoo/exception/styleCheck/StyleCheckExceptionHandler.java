package com.splitface.tattoo.exception.styleCheck;

import com.splitface.tattoo.exception.styleCheck.styleCheckExceptions.StyleNotContainedInListException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StyleCheckExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleStyleNotContainedInListException(StyleNotContainedInListException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
