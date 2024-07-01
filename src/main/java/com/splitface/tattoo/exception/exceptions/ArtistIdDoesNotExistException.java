package com.splitface.tattoo.exception.exceptions;

public class ArtistIdDoesNotExistException extends RuntimeException{
    public ArtistIdDoesNotExistException(String info){
        super(info);
    }
}
