package com.splitface.tattoo.exception.service.serviceExceptions;

public class ArtistIdDoesNotExistException extends RuntimeException{
    public ArtistIdDoesNotExistException(String info){
        super(info);
    }
}
