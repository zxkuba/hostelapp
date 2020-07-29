package com.zxkuba.reservationapp.exception;

public class ReservationNotFoundException extends Exception{
    private static final String EXCEPTION_MESSAGE = "No such RESERVATION_ID in Database";

    public ReservationNotFoundException(){
        super(EXCEPTION_MESSAGE);
    }
}
