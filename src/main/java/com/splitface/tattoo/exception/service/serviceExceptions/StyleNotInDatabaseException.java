package com.splitface.tattoo.exception.service.serviceExceptions;

public class StyleNotInDatabaseException extends RuntimeException{
    public StyleNotInDatabaseException(String info){
        super(info);
    }
}
