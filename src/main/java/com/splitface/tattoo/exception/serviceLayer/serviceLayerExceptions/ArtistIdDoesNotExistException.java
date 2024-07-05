package com.splitface.tattoo.exception.serviceLayer.serviceLayerExceptions;

public class ArtistIdDoesNotExistException extends RuntimeException{
    public ArtistIdDoesNotExistException(String info){
        super(info);
    }
}
