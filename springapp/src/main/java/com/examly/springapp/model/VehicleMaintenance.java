package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class VehicleMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceName;
    private int servicePrice;
    private String typeOfVehicle;

    public VehicleMaintenance(String serviceName, int servicePrice, String typeOfVehicle) {
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.typeOfVehicle = typeOfVehicle;
    }

}