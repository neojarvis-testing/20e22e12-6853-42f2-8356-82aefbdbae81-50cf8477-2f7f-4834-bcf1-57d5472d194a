package com.examly.springapp.model;
 
import java.time.LocalDate;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "serviceId")
    private VehicleMaintenance service;
    private LocalDate appointmentDate;
    private String location;
    private String status = "Pending";
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;
 
 
    public Appointment(VehicleMaintenance service, LocalDate appointmentDate, String location,
            String status, User user) {
        this.service = service;
        this.appointmentDate = appointmentDate;
        this.location = location;
        this.status = status;
        this.user = user;
    }
 
}
 

 
 