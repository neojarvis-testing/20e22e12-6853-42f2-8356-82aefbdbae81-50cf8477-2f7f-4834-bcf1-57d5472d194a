package com.examly.springapp.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.stereotype.Service;
 
import com.examly.springapp.model.Appointment;
import com.examly.springapp.model.User;
import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.repository.AppointmentRepo;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.repository.VehicleServiceRepo;
 
@Service
public class AppointmentServiceImpl implements AppointmentService {
 
    private final AppointmentRepo appointmentRepo;
    private final UserRepo userRepo;
    private final VehicleServiceRepo vehicleServiceRepo;
 
     /**
     * Constructor-based dependency injection ensures that repository instances are provided
     * at the time of object creation, improving maintainability and testability.
     *
     * @param appointmentRepo Repository for managing appointment-related database operations
     * @param userRepo Repository for managing user-related database operations
     * @param vehicleServiceRepo Repository for managing vehicle service-related database operations
     */
    public AppointmentServiceImpl(AppointmentRepo appointmentRepo, UserRepo userRepo, VehicleServiceRepo vehicleServiceRepo) {
        this.appointmentRepo = appointmentRepo;
        this.userRepo = userRepo;
        this.vehicleServiceRepo=vehicleServiceRepo;
    }
     /**
     * Adds a new appointment after validating that the associated user and service exist.
     * 
     * @param appointment The appointment object to be saved
     * @return The newly created appointment, or null if validation fails
     */
 
    public Appointment addAppointment(Appointment appointment) {
        VehicleMaintenance existingService=appointment.getService();
        User existingUser=appointment.getUser();
        if(existingService==null || existingUser==null){
            return null;
        }
        appointment.setService(existingService);
        appointment.setUser(existingUser);
        return appointmentRepo.save(appointment);
    }
     /**
     * Retrieves all appointments associated with a specific user.
     * 
     * @param userId The unique identifier of the user
     * @return List of appointments if the user exists, otherwise null
     */
 
    public List<Appointment> getAppointmentsbyUserId(int userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return null; // Exceptions will be added in future
        }
        return appointmentRepo.findByUserId(userId);
    }

     /**
     * Retrieves all stored appointments.
     * 
     * @return List of all appointments
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }

    /**
     * Updates an existing appointment's details.
     * 
     * @param appointment Updated appointment object
     * @param appointmentId Unique identifier of the appointment to be updated
     * @return The updated appointment, or null if the appointment is not found
     */
 
    public Appointment updateAppointment(Appointment appointment, long appointmentId) {
        Appointment appointment2 = appointmentRepo.findById(appointmentId).orElse(null);
        if (appointment2 == null) {
            return null;  // Exceptions will be added in future
        }
        appointment.setAppointmentId(appointmentId);
        User user=appointment.getUser();
        VehicleMaintenance vehicleMaintenance=appointment.getService();
        userRepo.save(user);
        vehicleServiceRepo.save(vehicleMaintenance);
 
        return appointmentRepo.save(appointment);
    }
     /**
     * Deletes an appointment by its unique ID.
     * 
     * @param appointmentId Unique identifier of the appointment to be deleted
     * @return Success message if deleted, otherwise message indicating the appointment was not found
     */
 
    public String deleteAppointment(long appointmentId) {
        Appointment appointment3 = appointmentRepo.findById(appointmentId).orElse(null);
        if (appointment3 == null) {
            return "Appointment deleted successfully";
        }
        appointmentRepo.deleteById(appointmentId);
        return "Appointment with ID: "+appointmentId+" not found";
    }
    /**
     * Retrieves an appointment by its unique ID.
     * 
     * @param appointmentId Unique identifier of the appointment
     * @return Optional containing the appointment if found
     */
 
    public Optional<Appointment> getAppointmentById(long appointmentId) {
        return appointmentRepo.findById(appointmentId);
    }
     /**
     * Retrieves the last appointment for a specific user.
     * 
     * @param userId The unique identifier of the user
     * @return The last appointment associated with the user
     */
    public Appointment getLastAppointmentbyUserId(int userId) {
        return appointmentRepo.getLastAppointmentbyUserId(userId);
    }
}