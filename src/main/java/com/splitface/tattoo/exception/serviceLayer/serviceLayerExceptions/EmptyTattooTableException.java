package com.splitface.tattoo.exception.serviceLayer.serviceLayerExceptions;

public class EmptyTattooTableException extends RuntimeException {

    public EmptyTattooTableException(String info){
        super(info);
    }
}
