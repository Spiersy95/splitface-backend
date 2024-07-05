package com.splitface.tattoo.exception.styleCheck.styleCheckExceptions;

public class StyleNotContainedInListException extends RuntimeException{
    public StyleNotContainedInListException(String info){
        super(info);
    }
}
