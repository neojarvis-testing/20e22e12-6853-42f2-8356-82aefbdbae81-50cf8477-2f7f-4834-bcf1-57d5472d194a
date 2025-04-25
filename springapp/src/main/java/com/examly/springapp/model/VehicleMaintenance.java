package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a vehicle maintenance service in the system.
 * Each service has a unique ID, name, price, and associated vehicle type.
 */

@Entity
public class VehicleMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;
    private String serviceName;
    private int servicePrice;
    private String typeOfVehicle;
    public Long getServiceId() {
        return serviceId;
    }
    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }
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

    //Default constructor.

    public VehicleMaintenance() {
    }

    /**
     * Parameterized constructor for creating a service instance with predefined values.
     *
     * @param serviceId Unique identifier for the service
     * @param serviceName Name of the maintenance service
     * @param servicePrice Cost associated with the service
     * @param typeOfVehicle Vehicle type applicable for the service
     */

    public VehicleMaintenance(Long serviceId, String serviceName, int servicePrice, String typeOfVehicle) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.typeOfVehicle = typeOfVehicle;
    }

}
