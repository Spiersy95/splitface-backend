package com.splitface.tattoo.exception.exceptions;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String info){
        super(info);
    }
}
