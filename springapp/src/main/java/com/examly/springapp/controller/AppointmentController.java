package com.examly.springapp.controller;
 
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.examly.springapp.model.Appointment;
import com.examly.springapp.model.AppointmentDTO;
import com.examly.springapp.service.AppointmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@RestController
@Tag(name = "Appointment Service Controller", description = "APIs for managing Appointment services.")
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
 
 
 
 
    @Operation(summary = "Create a new appointment", description = "Allows a user to create a new appointment for vehicle maintenance.")
    @PostMapping("/api/appointments")
    public ResponseEntity<AppointmentDTO> addAppointments(@Valid @RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.status(201).body(appointmentService.addAppointments(appointmentDTO));
    }   
   
    @Operation(summary = "Get appointments by user ID", description = "Retrieves all appointments linked to a specific user by their ID.")
    @GetMapping("/api/appointment/user/{userId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsbyUserId(@PathVariable int userId) {
        return ResponseEntity.status(200).body(appointmentService.getAppointmentsbyUserId(userId));
    }
 
    @Operation(summary = "Get last appointment for user", description = "Fetches the most recent appointment for a specific user.")
    @GetMapping("/api/appointment/user/{userId}/last") // End Point Get Last Appointment Date for the particular User
    public ResponseEntity<AppointmentDTO> getLastAppointmentbyUserId(@PathVariable int userId) {
        return ResponseEntity.status(200).body(appointmentService.getLastAppointmentbyUserId(userId));
    }
   
    @Operation(summary = "Get all appointments", description = "Retrieves a list of all appointments.")
    @GetMapping("/api/appointments")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
       return ResponseEntity.status(200).body(appointmentService.getAllAppointments());
    }
 
    @Operation(summary = "Update appointment by ID", description = "Updates details of an existing appointment by its ID.")
    @PutMapping("/api/appointments/{appointmentId}")
    public ResponseEntity<AppointmentDTO> updateAppointments(@Valid @RequestBody AppointmentDTO appointmentDTO,
            @PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.updateAppointments(appointmentDTO, appointmentId));
    }
 
    @Operation(summary = "Delete appointment by ID", description = "Deletes an appointment by its unique ID.")
    @DeleteMapping("/api/appointment/{appointmentId}")
    public ResponseEntity<Map<String,String>> deleteAppointment(@PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.deleteAppointment(appointmentId));
    }
 
    @Operation(summary = "Get appointment by ID", description = "Fetches an appointment by its unique ID.")
    @GetMapping("/api/appointments/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentsById(@PathVariable long id) {
        return ResponseEntity.status(200).body(appointmentService.getAppointmentsById(id));
    }
 
    @Operation(summary = "Appointments Sort By Appointment Date", description = "Shows all the appointments using pagination and sorting.")
        @GetMapping("/appointments")
        public List<AppointmentDTO> getAppointmentsWithPagingAndSorting(
                @RequestParam(defaultValue = "0") Integer pageNo,
                @RequestParam(defaultValue = "10") Integer pageSize,
                @RequestParam(defaultValue = "appointmentDate") String sortBy,
                @RequestParam(defaultValue = "asc") String sortDir) {
 
            // Pass sorting direction to the service layer
            return appointmentService.getAppointmentsByPagination(pageNo, pageSize, sortBy, sortDir);
    }
 
}