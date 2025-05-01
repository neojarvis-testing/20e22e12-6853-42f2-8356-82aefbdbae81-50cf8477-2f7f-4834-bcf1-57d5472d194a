package com.examly.springapp.model;

import java.time.LocalDate;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
   
    private Long id;
    @NotNull(message = "Service ID is required")
    private Long serviceId;

    @NotNull(message = "Appointment date is required")
    @UpdateTimestamp
    private LocalDate appointmentDate;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "User ID is required")
    private int userId;
    
}