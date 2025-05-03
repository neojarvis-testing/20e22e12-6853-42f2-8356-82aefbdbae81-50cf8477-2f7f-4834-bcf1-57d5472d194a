package com.examly.springapp.model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VehicleMaintenanceDTO {
    private Long id;
    @NotBlank(message = "Service name cannot be empty")
    @Size(min = 3, max = 50, message = "Service name must be between 3 and 50 characters")
    private String serviceName;

    @Min(value = 0, message = "Service price must be non-negative")
    private int servicePrice;

    @NotBlank(message = "Type of vehicle cannot be empty")
    @Size(min = 3, max = 30, message = "Type of vehicle must be between 3 and 30 characters")
    private String typeOfVehicle;
    
}