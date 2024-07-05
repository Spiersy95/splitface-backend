package com.splitface.tattoo.exception.artistCheck;

import com.splitface.tattoo.exception.artistCheck.artistCheckExceptions.EmailValidatorException;
import com.splitface.tattoo.exception.artistCheck.artistCheckExceptions.NameValidatorException;
import com.splitface.tattoo.exception.artistCheck.artistCheckExceptions.PasswordValidatorException;
import com.splitface.tattoo.exception.artistCheck.artistCheckExceptions.PostcodeValidatorException;
import com.splitface.tattoo.exception.serviceLayer.serviceLayerExceptions.ArtistIdDoesNotExistException;
import com.splitface.tattoo.exception.serviceLayer.serviceLayerExceptions.TattooMatchingStyleIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ArtistCheckExceptionHandler {

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
    public ResponseEntity<Object> handlePostcodeValidatorException(PostcodeValidatorException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleEmailValidatorException(EmailValidatorException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }
}
