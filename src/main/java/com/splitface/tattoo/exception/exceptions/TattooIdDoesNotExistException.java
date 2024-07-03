package com.splitface.tattoo.exception.exceptions;

public class TattooIdDoesNotExistException extends RuntimeException{

    public TattooIdDoesNotExistException(String info){
        super(info);
    }
}
