package com.zxkuba.reservationapp.exception;

public class CheckOutNotFoundException extends Exception{

    private static final String EXCEPTION_MESSAGE = "No such Check_Out_ID in Database";

    public CheckOutNotFoundException(){
        super(EXCEPTION_MESSAGE);
    }
}
