package com.splitface.tattoo.exception.exceptions;

public class EmptyTattooTableException extends RuntimeException {

    public EmptyTattooTableException(String info){
        super(info);
    }
}
