package com.splitface.tattoo.exception.exceptions;

public class InvalidPostcodeException extends RuntimeException{

    public InvalidPostcodeException(String info){
        super(info);
    }
}
