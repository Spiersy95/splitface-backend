package com.splitface.tattoo.exception;

public class EmptyTattooTableException extends RuntimeException {

    public EmptyTattooTableException(String info){
        super(info);
    }
}
