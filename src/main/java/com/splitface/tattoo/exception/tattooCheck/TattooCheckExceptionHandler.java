package com.splitface.tattoo.exception.tattooCheck;

import com.splitface.tattoo.exception.tattooCheck.tattooCheckExceptions.InvalidHoursWorkedException;
import com.splitface.tattoo.exception.tattooCheck.tattooCheckExceptions.InvalidPriceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TattooCheckExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidPriceException(InvalidPriceException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidPriceException(InvalidHoursWorkedException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
