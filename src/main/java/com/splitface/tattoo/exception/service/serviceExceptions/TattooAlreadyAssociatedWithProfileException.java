package com.splitface.tattoo.exception.service.serviceExceptions;

public class TattooAlreadyAssociatedWithProfileException extends RuntimeException{

    public TattooAlreadyAssociatedWithProfileException(String info){
        super(info);
    }
}
