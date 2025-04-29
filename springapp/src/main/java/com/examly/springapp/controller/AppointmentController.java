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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.examly.springapp.model.Appointment;
import com.examly.springapp.model.AppointmentDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.service.AppointmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.transaction.Transactional;
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

    // @PostMapping("/api/appointment")
    // public ResponseEntity<AppointmentDTO> addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
    //     return ResponseEntity.status(201).body(appointmentService.addAppointment(appointmentDTO));
    // }
 


    @Operation(summary = "Create a new appointment", description = "Allows a user to create a new appointment for vehicle maintenance.")
    @PostMapping("/api/appointments")
    public ResponseEntity<AppointmentDTO> addAppointments(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.status(201).body(appointmentService.addAppointments(appointmentDTO));
    }

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
   

    
    @Operation(summary = "Get appointments by user ID", description = "Retrieves all appointments linked to a specific user by their ID.")

    @GetMapping("/api/appointment/user/{userId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsbyUserId(@PathVariable int userId) {
        return ResponseEntity.status(200).body(appointmentService.getAppointmentsbyUserId(userId));
    }
    @GetMapping("/api/appointment/{userId}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsbyUser(@PathVariable int userId) {
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
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointmentDTO,
            @PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.updateAppointment(appointmentDTO, appointmentId));
    }

    @Operation(summary = "Update appointment by ID", description = "Updates details of an existing appointment by its ID.")
    @PutMapping("/api/appointments/{appointmentId}")
    public ResponseEntity<AppointmentDTO> updateAppointments(@RequestBody AppointmentDTO appointmentDTO,
            @PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.updateAppointments(appointmentDTO, appointmentId));
    }
 
    @Operation(summary = "Delete appointment by ID", description = "Deletes an appointment by its unique ID.")
    @DeleteMapping("/api/appointment/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.deleteAppointment(appointmentId));
    }

    // @Operation(summary = "Get appointment by ID", description = "Fetches an appointment by its unique ID.")
    // @GetMapping("/api/appointment/{id}")
    // public ResponseEntity<Object> getAppointmentsById(@PathVariable long id) {
    //     return ResponseEntity.status(200).body(appointmentService.getAppointmentsById(id).get());
    // }

    @Operation(summary = "Appointments Sort By Appointment Date", description = "Shows all the appointments using pagination and sorting.")
        @GetMapping("/appointments")
        public List<Appointment> getAppointmentsWithPagingAndSorting(
                @RequestParam(defaultValue = "0") Integer pageNo,
                @RequestParam(defaultValue = "10") Integer pageSize,
                @RequestParam(defaultValue = "appointmentDate") String sortBy,
                @RequestParam(defaultValue = "asc") String sortDir) {
 
            // Pass sorting direction to the service layer
            return appointmentService.getAppointmentsByPagination(pageNo, pageSize, sortBy, sortDir);
    }
}

