package com.splitface.tattoo.exception.tattooCheck.tattooCheckExceptions;

public class InvalidPriceException extends RuntimeException {

    public InvalidPriceException(String info){
        super(info);
    }
}
