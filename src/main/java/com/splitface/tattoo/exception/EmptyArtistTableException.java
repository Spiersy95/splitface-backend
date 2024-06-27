package com.splitface.tattoo.exception;

public class EmptyArtistTableException extends RuntimeException{

    EmptyArtistTableException(String info){
        super(info);
    }
}
