package com.splitface.tattoo.exception.service.serviceExceptions;

public class TattooIdDoesNotExistException extends RuntimeException{

    public TattooIdDoesNotExistException(String info){
        super(info);
    }
}
