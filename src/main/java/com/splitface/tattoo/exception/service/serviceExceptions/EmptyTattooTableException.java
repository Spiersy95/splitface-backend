package com.splitface.tattoo.exception.service.serviceExceptions;

public class EmptyTattooTableException extends RuntimeException {

    public EmptyTattooTableException(String info){
        super(info);
    }
}
