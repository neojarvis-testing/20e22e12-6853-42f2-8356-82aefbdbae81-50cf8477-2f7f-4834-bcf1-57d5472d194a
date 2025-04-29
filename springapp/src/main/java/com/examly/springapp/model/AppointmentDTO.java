package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppointmentDTO {
   

    @NotNull(message = "Service ID is required")
    private Long id;

    @NotNull(message = "Appointment date is required")
    private LocalDate appointmentDate;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "User ID is required")
    private int userId;

    public AppointmentDTO(@NotNull(message = "Service ID is required") Long id,
            @NotNull(message = "Appointment date is required") LocalDate appointmentDate,
            @NotBlank(message = "Location is required") String location,
            @NotBlank(message = "Status is required") String status,
            @NotNull(message = "User ID is required") int userId) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.location = location;
        this.status = status;
        this.userId = userId;
    }

    public AppointmentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setServiceId(Long id) {
        this.id = id;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    
}

