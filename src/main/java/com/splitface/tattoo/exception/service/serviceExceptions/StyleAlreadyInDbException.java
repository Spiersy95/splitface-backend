package com.splitface.tattoo.exception.service.serviceExceptions;

public class StyleAlreadyInDbException extends RuntimeException{
    public StyleAlreadyInDbException(String info){
        super(info);
    }
}
