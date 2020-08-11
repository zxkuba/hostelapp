package com.zxkuba.reservationapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoElementFoundException.class)
    public ResponseEntity<Object> noElementException(NoElementFoundException e, WebRequest webRequest){
        System.err.println(Arrays.toString(e.getStackTrace()));
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<Object> noRoomException(RoomNotFoundException e, WebRequest webRequest) {
        System.err.println(Arrays.toString(e.getStackTrace()));
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(ResidentNotFoundException.class)
    public ResponseEntity<Object> noResidentException(ResidentNotFoundException e, WebRequest webRequest) {
        System.err.println(Arrays.toString(e.getStackTrace()));
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Object> noReservationException(ReservationNotFoundException e, WebRequest webRequest) {
        System.err.println(Arrays.toString(e.getStackTrace()));
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(CheckOutNotFoundException.class)
    public ResponseEntity<Object> noCheckOutException(CheckOutNotFoundException e, WebRequest webRequest) {
        System.err.println(Arrays.toString(e.getStackTrace()));
        return handleExceptionInternal(e, e.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, webRequest);
    }

}
