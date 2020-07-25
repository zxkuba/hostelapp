package com.zxkuba.reservationapp.exception;

public class NoElementFoundException extends Exception{
    private static final String EXCEPTION_MESSAGE = "No such ID in Database";

    public NoElementFoundException(){
        super(EXCEPTION_MESSAGE);
    }
}
