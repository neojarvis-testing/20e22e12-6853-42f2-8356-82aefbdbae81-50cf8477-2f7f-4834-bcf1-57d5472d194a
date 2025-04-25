package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents an appointment entity in the system.
 * Each appointment is associated with a specific user and vehicle maintenance service.
 */

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    @ManyToOne
    @JoinColumn(name = "serviceId")
    private VehicleMaintenance service; 
    private LocalDate appointmentDate;
    private String location;
    private String status = "Pending";
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    // Getter and Setter methods to access and modify appointment details

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public VehicleMaintenance getService() {
        return service;
    }

    public void setService(VehicleMaintenance service) {
        this.service = service;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

     // Default constructor.

    public Appointment() {
    }

    /**
     * Parameterized constructor for creating an appointment instance.
     *
     * @param appointmentId Unique identifier for the appointment
     * @param service Associated vehicle maintenance service
     * @param appointmentDate Date of the appointment
     * @param location Location of the appointment
     * @param status Current status of the appointment (e.g., Pending, Completed)
     * @param user User who scheduled the appointment
     */

    public Appointment(Long appointmentId, VehicleMaintenance service, LocalDate appointmentDate, String location,
            String status, User user) {
        this.appointmentId = appointmentId;
        this.service = service;
        this.appointmentDate = appointmentDate;
        this.location = location;
        this.status = status;
        this.user = user;
    }

    public Appointment(VehicleMaintenance service, LocalDate appointmentDate, String location,
            String status, User user) {
        this.service = service;
        this.appointmentDate = appointmentDate;
        this.location = location;
        this.status = status;
        this.user = user;
    }

}
