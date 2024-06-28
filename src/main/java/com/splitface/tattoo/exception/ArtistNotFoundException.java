package com.splitface.tattoo.exception;

public class ArtistNotFoundException extends RuntimeException{
    public ArtistNotFoundException(String message) {
        super(message);
    }
}
