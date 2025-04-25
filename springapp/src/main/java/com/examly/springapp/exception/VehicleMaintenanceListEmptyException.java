package com.examly.springapp.exception;

public class VehicleMaintenanceListEmptyException extends RuntimeException{
    public VehicleMaintenanceListEmptyException(String msg){
        super(msg);
    }
}
