package com.examly.springapp.model;
 
import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
 
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne(cascade = CascadeType.REMOVE)  // Ensures deletion of dependent records
    @JoinColumn(name = "serviceId")
    private VehicleMaintenance service;
 
    private LocalDate appointmentDate;
    private String location;
    private String status = "Pending";
 
    @ManyToOne(cascade = CascadeType.REMOVE)  // Allows safe deletion of appointment without FK conflicts
    @JoinColumn(name = "userId")
    private User user;
 
    public Appointment() {
    }
 
    public Appointment(Long id, VehicleMaintenance service, LocalDate appointmentDate, String location,
            String status, User user) {
        this.id = id;
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
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
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
}