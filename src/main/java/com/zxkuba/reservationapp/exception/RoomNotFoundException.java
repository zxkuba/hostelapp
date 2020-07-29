package com.zxkuba.reservationapp.exception;

public class RoomNotFoundException extends Exception{
    private static final String EXCEPTION_MESSAGE = "No such ROOM_ID in Database";

    public RoomNotFoundException(){
        super(EXCEPTION_MESSAGE);
    }
}
