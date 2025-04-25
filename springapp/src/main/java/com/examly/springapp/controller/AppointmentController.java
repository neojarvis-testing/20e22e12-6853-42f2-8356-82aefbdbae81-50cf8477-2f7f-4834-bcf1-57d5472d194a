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
 
   /**
     * Constructor-based injection ensures that the AppointmentServiceImpl instance
     * is provided at the time of object creation, promoting better testability.
     *
     * @param appointmentService The service instance for handling appointment logic
     */

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }
     /**
     * Handles creation of a new appointment.
     *
     * @param appointment The appointment details provided in the request body
     * @return ResponseEntity containing the newly created appointment with HTTP status 201 (Created)
     */
 
    @PostMapping("/api/appointment")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        appointment = appointmentService.addAppointment(appointment);
        return ResponseEntity.status(201).body(appointment);
    }
    /**
     * Retrieves all appointments associated with a specific user.
     *
     * @param userId Unique identifier of the user
     * @return ResponseEntity containing a list of appointments for the user with HTTP status 200 (OK)
     */
 
    @GetMapping("/api/appointment/user/{userId}")
    public ResponseEntity<Object> getAppointmentsbyUserId(@PathVariable int userId) {
        return ResponseEntity.status(200).body(appointmentService.getAppointmentsbyUserId(userId));
    }
     /**
     * Retrieves the latest appointment details for a specific user.
     *
     * @param userId Unique identifier of the user
     * @return ResponseEntity containing the latest appointment details with HTTP status 200 (OK)
     */
    @GetMapping("/api/appointment/user/{userId}/last")
    public ResponseEntity<Object> getLastAppointmentbyUserId(@PathVariable int userId) {
        return ResponseEntity.status(200).body(appointmentService.getLastAppointmentbyUserId(userId));
    }

     /**
     * Retrieves a specific appointment by its ID.
     *
     * @param appointmentId Unique identifier of the appointment
     * @return ResponseEntity containing the appointment details with HTTP status 200 (OK)
     * Note: `.get()` is used on an Optional value, which may lead to exceptions if the appointment is not found.
     * Consider adding proper error handling before calling `.get()`.
     */
 
    @GetMapping("/api/appointment/{appointmentId}")
    public ResponseEntity<Object> getAppointmentById(@PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.getAppointmentById(appointmentId).get());
    }
     /**
     * Retrieves all stored appointments.
     *
     * @return ResponseEntity containing a list of all appointments with HTTP status 200 (OK)
     */
 
    @GetMapping("/api/appointment")
    public ResponseEntity<Object> getAllAppointments() {
       return ResponseEntity.status(200).body(appointmentService.getAllAppointments());
    }
    /**
     * Updates an existing appointment.
     *
     * @param appointmentId Unique identifier of the appointment to be updated
     * @param appointment Updated appointment object with new details
     * @return ResponseEntity containing the modified appointment with HTTP status 200 (OK)
     */
 
    @PutMapping("/api/appointment/{appointmentId}")
    public ResponseEntity<Object> updateAppointment(@RequestBody Appointment appointment,
            @PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.updateAppointment(appointment, appointmentId));
    }
    /**
     * Deletes an appointment by its ID.
     *
     * @param appointmentId Unique identifier of the appointment to be deleted
     * @return ResponseEntity containing a success message with HTTP status 200 (OK)
     * Note: Consider handling the case where the appointment does not exist, e.g., returning HTTP status 404.
     */
    @DeleteMapping("/api/appointment/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable long appointmentId) {
        return ResponseEntity.status(200).body(appointmentService.deleteAppointment(appointmentId));
    }
}