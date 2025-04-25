package com.examly.springapp.exception;

public class AppointmentListEmptyException extends RuntimeException{
    public AppointmentListEmptyException(String msg){
        super(msg);
    }
}