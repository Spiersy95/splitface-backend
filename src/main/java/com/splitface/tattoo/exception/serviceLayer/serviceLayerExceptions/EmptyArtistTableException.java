package com.splitface.tattoo.exception.serviceLayer.serviceLayerExceptions;

public class EmptyArtistTableException extends RuntimeException{

    public EmptyArtistTableException(String info){
        super(info);
    }
}
