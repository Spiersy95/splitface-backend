package com.splitface.tattoo.exception;


import com.splitface.tattoo.exception.exceptions.*
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleEmptyArtistTableException(EmptyArtistTableException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  
    @ExceptionHandler
    public ResponseEntity<Object> handleEmptyTattooTableException(EmptyTattooTableException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<Object> handlePasswordValidatorException(PasswordValidatorException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<Object> handleNameValidatorException(NameValidatorException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<Object> handleMatchingStyleIdException(TattooMatchingStyleIdException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleArtistIdException(ArtistIdDoesNotExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleEmptyStyleTableException(EmptyStyleTableException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }





}
