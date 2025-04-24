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
 
    // Constructor-based dependency injection
    public AppointmentServiceImpl(AppointmentRepo appointmentRepo, UserRepo userRepo, VehicleServiceRepo vehicleServiceRepo) {
        this.appointmentRepo = appointmentRepo;
        this.userRepo = userRepo;
        this.vehicleServiceRepo=vehicleServiceRepo;
    }
 
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
 
    public List<Appointment> getAppointmentsbyUserId(int userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return null; // Exceptions will be added in future
        }
        return appointmentRepo.findByUserId(userId);
    }
 
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }
 
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
 
    public String deleteAppointment(long appointmentId) {
        Appointment appointment3 = appointmentRepo.findById(appointmentId).orElse(null);
        if (appointment3 == null) {
            return "Appointment deleted successfully";
        }
        appointmentRepo.deleteById(appointmentId);
        return "Appointment with ID: "+appointmentId+" not found";
    }
 
    public Optional<Appointment> getAppointmentById(long appointmentId) {
        return appointmentRepo.findById(appointmentId);
    }
 
    public Appointment getLastAppointmentbyUserId(int userId) {
        return appointmentRepo.getLastAppointmentbyUserId(userId);
    }
}
 