package com.examly.springapp.model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VehicleMaintenanceDTO {

    @NotBlank(message = "Service name cannot be empty")
    @Size(min = 3, max = 50, message = "Service name must be between 3 and 50 characters")
    private String serviceName;

    @Min(value = 0, message = "Service price must be non-negative")
    private int servicePrice;

    @NotBlank(message = "Type of vehicle cannot be empty")
    @Size(min = 3, max = 30, message = "Type of vehicle must be between 3 and 30 characters")
    private String typeOfVehicle;

    // Constructor
    public VehicleMaintenanceDTO(String serviceName, int servicePrice, String typeOfVehicle) {
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.typeOfVehicle = typeOfVehicle;
    }
    
    public VehicleMaintenanceDTO() {
    }


    // Getters and setters
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }
}

