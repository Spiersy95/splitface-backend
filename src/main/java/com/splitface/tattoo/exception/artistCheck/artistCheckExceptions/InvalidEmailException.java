package com.splitface.tattoo.exception.artistCheck.artistCheckExceptions;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String info){
        super(info);
    }
}
