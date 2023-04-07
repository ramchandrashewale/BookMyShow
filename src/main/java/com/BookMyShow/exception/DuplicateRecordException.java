package com.BookMyShow.exception;

public class DuplicateRecordException extends RuntimeException{
    private final String message;

    public DuplicateRecordException(String message){
        super(message);
        this.message=message;
    }
}
