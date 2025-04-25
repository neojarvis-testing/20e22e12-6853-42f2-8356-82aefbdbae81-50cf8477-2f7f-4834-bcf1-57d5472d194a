package com.examly.springapp.exception;

public class VehicleMaintenanceServiceNotFoundException extends RuntimeException{
    public VehicleMaintenanceServiceNotFoundException(String msg){
        super(msg);
    }
}
