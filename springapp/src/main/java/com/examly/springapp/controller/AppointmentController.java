package com.examly.springapp.controller;
 
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.examly.springapp.model.Appointment;
 
import com.examly.springapp.service.AppointmentServiceImpl;
 
@RestController
public class AppointmentController {
 
    private final AppointmentServiceImpl appointmentService;
 
    // Constructor-based injection
    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }
 
    @PostMapping("/api/appointment")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        appointment = appointmentService.addAppointment(appointment);
        return ResponseEntity.status(201).body(appointment);
    }
 
    @GetMapping("/api/appointment/user/{userId}")
    public ResponseEntity<Object> getAppointmentsbyUserId(@PathVariable int userId) {
        return ResponseEntity.status(200).body(appointmentService.getAppointmentsbyUserId(userId));
    }
 
    @GetMapping("/api/appointment/user/{userId}/last") // End Point Get Last Appointment Date for the particular User
    public ResponseEntity<Object> getLastAppointmentbyUserId(@PathVariable int userId) {
        return ResponseEntity.status(200).body(appointmentService.getLastAppointmentbyUserId(userId));
    }
 
    @GetMapping("/api/appointment/{appointmentId}")
    public ResponseEntity<Object> getAppointmentById(@PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.getAppointmentById(appointmentId).get());
    }
 
    @GetMapping("/api/appointment")
    public ResponseEntity<Object> getAllAppointments() {
       return ResponseEntity.status(200).body(appointmentService.getAllAppointments());
    }
 
    @PutMapping("/api/appointment/{appointmentId}")
    public ResponseEntity<Object> updateAppointment(@RequestBody Appointment appointment,
            @PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.updateAppointment(appointment, appointmentId));
    }
 
    @DeleteMapping("/api/appointment/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.deleteAppointment(appointmentId));
    }
}
 