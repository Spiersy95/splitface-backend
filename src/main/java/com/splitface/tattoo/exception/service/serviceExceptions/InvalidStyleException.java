package com.splitface.tattoo.exception.service.serviceExceptions;

public class InvalidStyleException extends RuntimeException{
    public InvalidStyleException(String info){
        super(info);
    }
}
