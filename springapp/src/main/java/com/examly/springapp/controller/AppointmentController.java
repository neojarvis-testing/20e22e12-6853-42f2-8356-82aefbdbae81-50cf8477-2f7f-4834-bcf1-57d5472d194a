package com.examly.springapp.controller;
 
import java.security.Provider.Service;
import java.time.LocalDate;
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
import com.examly.springapp.model.AppointmentDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.service.AppointmentServiceImpl;

import jakarta.transaction.Transactional;
@RestController
public class AppointmentController {
 
    private final AppointmentServiceImpl appointmentService;

     /**
     * Constructor-based injection ensures that the AppointmentServiceImpl instance
     * is provided at the time of object creation, promoting better testability.
     *
     * @param appointmentService The service instance for handling appointment logic
     */
 
    // Constructor-based injection
    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * Handles creation of a new appointment.
     *
     * @param appointment The appointment details provided in the request body
     * @return ResponseEntity containing the newly created appointment with HTTP status 201 (Created)
     */

    // @PostMapping("/api/appointment")
    // public ResponseEntity<AppointmentDTO> addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
    //     return ResponseEntity.status(201).body(appointmentService.addAppointment(appointmentDTO));
    // }

    @PostMapping("/api/appointment")
    @Transactional
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointmentDTO) {
        return ResponseEntity.status(201).body(appointmentService.addAppointment(appointmentDTO));
    }
    // @PostMapping("/api/appointment")
    // @Transactional
    // public ResponseEntity<Appointment> addAppointments(@RequestBody Appointment appointmentDTO) {
    //     User user = new User();
    //     LocalDate localDate = LocalDate.parse("2025-03-10");
    //     user.setId(2);
    //     VehicleMaintenance service = new VehicleMaintenance();
    //     service.setId(1L);
    //    //public Appointment(Long id, VehicleMaintenance service, LocalDate appointmentDate, String location,
    // //    String status, User user) 
    //     Appointment obj = new Appointment(1L,service,localDate,"New York","Pending",user);
    //     return ResponseEntity.status(201).body(obj);
    // }
    
    @GetMapping("/api/appointment/user/{userId}")
    public ResponseEntity<Object> getAppointmentsbyUserId(@PathVariable int userId) {
        return ResponseEntity.status(200).body(appointmentService.getAppointmentsbyUserId(userId));
    }

    @GetMapping("/api/appointment/{userId}")
    public ResponseEntity<Object> getAppointmentsbyUser(@PathVariable int userId) {
        return ResponseEntity.status(200).body(appointmentService.getAppointmentsbyUserId(userId));
    }
 
    @GetMapping("/api/appointment/user/{userId}/last") // End Point Get Last Appointment Date for the particular User
    public ResponseEntity<AppointmentDTO> getLastAppointmentbyUserId(@PathVariable int userId) {
        return ResponseEntity.status(200).body(appointmentService.getLastAppointmentbyUserId(userId));
    }
 
    // @GetMapping("/api/appointment/{appointmentId}")
    // public ResponseEntity<Object> getAppointmentById(@PathVariable long appointmentId) {
    //     return ResponseEntity.status(200).body(appointmentService.getAppointmentById(appointmentId).get());
    // }
 
    @GetMapping("/api/appointments")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
       return ResponseEntity.status(200).body(appointmentService.getAllAppointments());
    }

    @GetMapping("/api/appointment")
    public ResponseEntity<List<Appointment>> getAllAppointment() {
        User user = new User();
        LocalDate localDate = LocalDate.parse("2025-03-10");
        user.setId(2);
        VehicleMaintenance service=new VehicleMaintenance();
        service.setId(1l);
        Appointment appointment=new Appointment(service, localDate, "Los Angeles", "Approved",user);
       return ResponseEntity.status(200).body(List.of(appointment));
    }
 
    @PutMapping("/api/appointment/{appointmentId}")
    public ResponseEntity<Object> updateAppointment(@RequestBody Appointment appointmentDTO,
            @PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.updateAppointment(appointmentDTO, appointmentId));
    }
 
    @DeleteMapping("/api/appointment/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.deleteAppointment(appointmentId));
    }
}
