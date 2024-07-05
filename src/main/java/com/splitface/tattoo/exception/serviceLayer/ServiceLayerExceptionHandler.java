package com.splitface.tattoo.exception.serviceLayer;

import com.splitface.tattoo.exception.serviceLayer.serviceLayerExceptions.EmptyArtistTableException;
import com.splitface.tattoo.exception.serviceLayer.serviceLayerExceptions.EmptyStyleTableException;
import com.splitface.tattoo.exception.serviceLayer.serviceLayerExceptions.EmptyTattooTableException;
import com.splitface.tattoo.exception.serviceLayer.serviceLayerExceptions.TattooIdDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceLayerExceptionHandler {

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
