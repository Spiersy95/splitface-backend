package com.splitface.tattoo.exception;

public class EmptyArtistTableException extends RuntimeException{

    public EmptyArtistTableException(String info){
        super(info);
    }
}
