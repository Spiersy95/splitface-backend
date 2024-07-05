package com.splitface.tattoo.exception.serviceLayer.serviceLayerExceptions;

public class TattooIdDoesNotExistException extends RuntimeException{

    public TattooIdDoesNotExistException(String info){
        super(info);
    }
}
