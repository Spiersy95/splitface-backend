package com.splitface.tattoo.exception.service.serviceExceptions;

public class EmptyArtistTableException extends RuntimeException{

    public EmptyArtistTableException(String info){
        super(info);
    }
}
