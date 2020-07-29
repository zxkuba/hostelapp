package com.zxkuba.reservationapp.exception;

public class ResidentNotFoundException extends Exception{
    private static final String EXCEPTION_MESSAGE = "No such RESIDENT_ID in Database";

    public ResidentNotFoundException(){
        super(EXCEPTION_MESSAGE);
    }
}
