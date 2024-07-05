package com.splitface.tattoo.exception.service;

import com.splitface.tattoo.exception.service.serviceExceptions.EmptyArtistTableException;
import com.splitface.tattoo.exception.service.serviceExceptions.EmptyStyleTableException;
import com.splitface.tattoo.exception.service.serviceExceptions.EmptyTattooTableException;
import com.splitface.tattoo.exception.service.serviceExceptions.TattooIdDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleEmptyArtistTableException(EmptyArtistTableException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleEmptyTattooTableException(EmptyTattooTableException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleEmptyStyleTableException(EmptyStyleTableException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleTattooIdDoesNotExistException(TattooIdDoesNotExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
