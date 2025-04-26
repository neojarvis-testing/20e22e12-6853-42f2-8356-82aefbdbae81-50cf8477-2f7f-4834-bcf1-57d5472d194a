package com.examly.springapp.service;
 
import java.util.List;
import java.util.Optional;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
 
import com.examly.springapp.model.Appointment;
import com.examly.springapp.model.AppointmentDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.repository.AppointmentRepo;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.repository.VehicleServiceRepo;
import com.examly.springapp.exception.AppointmentListEmptyException;
import com.examly.springapp.exception.AppointmentNotFoundException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.exception.VehicleMaintenanceServiceNotFoundException;
import com.examly.springapp.mapper.AppointmentMapper;
 
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);
    private final AppointmentRepo appointmentRepo;
    private final UserRepo userRepo;
    private final VehicleServiceRepo vehicleServiceRepo;
 
    // Constructor-based dependency injection
    public AppointmentServiceImpl(AppointmentRepo appointmentRepo, UserRepo userRepo, VehicleServiceRepo vehicleServiceRepo) {
        this.appointmentRepo = appointmentRepo;
        this.userRepo = userRepo;
        this.vehicleServiceRepo = vehicleServiceRepo;
    }
 
    public AppointmentDTO addAppointment(AppointmentDTO appointmentDTO) {
        logger.info("Adding appointment for user ID: {}", appointmentDTO.getUserId());
        VehicleMaintenance existingService = vehicleServiceRepo.findById(appointmentDTO.getServiceId()).orElse(null);
        if (existingService == null) {
            logger.error("Vehicle Maintenance Service not found with ID: {}", appointmentDTO.getServiceId());
            throw new VehicleMaintenanceServiceNotFoundException("Vehicle Maintenance Service with ID " + appointmentDTO.getServiceId() + " not found");
        }

        User existingUser = userRepo.findById(appointmentDTO.getUserId()).orElse(null);
        Appointment appointment = AppointmentMapper.mapAppointmentDTOToAppointment(appointmentDTO, existingService, existingUser);
        appointment.setService(existingService);
        appointment.setUser(existingUser);
        appointment = appointmentRepo.save(appointment);
        logger.info("Appointment successfully created with ID: {}", appointment.getAppointmentId());

        return AppointmentMapper.mapToAppointmentDTO(appointment);
    }
 
    public List<AppointmentDTO> getAppointmentsbyUserId(int userId) {
        logger.info("Fetching appointments for user ID: {}", userId);
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            logger.error("User not found with ID: {}", userId);
            throw new UserNotFoundException("User Name not found");
        }

        List<Appointment> appointmentList = appointmentRepo.findByUserId(userId);
        return appointmentList.stream().map(AppointmentMapper::mapToAppointmentDTO).toList();
    }
 
    public List<AppointmentDTO> getAllAppointments() {
        logger.info("Fetching all appointments");
        List<Appointment> appointmentList = appointmentRepo.findAll();
        if (appointmentList.isEmpty()) {
            logger.error("Appointment list is empty");
            throw new AppointmentListEmptyException("Appointment List is Empty");
        }
        return appointmentList.stream().map(AppointmentMapper::mapToAppointmentDTO).toList();
    }
 
    public AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO, long appointmentId) {
        logger.info("Updating appointment with ID: {}", appointmentId);
        Appointment existingAppointment = appointmentRepo.findById(appointmentId).orElse(null);
        if (existingAppointment == null) {
            logger.error("Appointment not found with ID: {}", appointmentId);
            return null;
        }

        VehicleMaintenance vehicleMaintenance = vehicleServiceRepo.findById(appointmentDTO.getServiceId()).orElse(null);
        User user = userRepo.findById(appointmentDTO.getUserId()).orElse(null);
        Appointment appointment = AppointmentMapper.mapAppointmentDTOToAppointment(appointmentDTO, vehicleMaintenance, user);
        appointment.setAppointmentId(appointmentId);

        appointmentRepo.save(appointment);
        logger.info("Appointment successfully updated for ID: {}", appointmentId);

        return AppointmentMapper.mapToAppointmentDTO(appointment);
    }
 
    public String deleteAppointment(long appointmentId) {
        logger.info("Deleting appointment with ID: {}", appointmentId);
        Appointment appointment = appointmentRepo.findById(appointmentId).orElse(null);
        if (appointment == null) {
            logger.error("Appointment not found with ID: {}", appointmentId);
            return "Appointment with ID: " + appointmentId + " not found";
        }

        appointmentRepo.deleteById(appointmentId);
        logger.info("Appointment successfully deleted with ID: {}", appointmentId);

        return "Appointment deleted successfully";
    }
 
    public Optional<Appointment> getAppointmentById(long appointmentId) {
        logger.info("Fetching appointment by ID: {}", appointmentId);
        Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);
        if (appointment.isEmpty()) {
            logger.error("Appointment not found with ID: {}", appointmentId);
            throw new AppointmentNotFoundException("Appointment with ID: " + appointmentId + " not found");
        }
        return appointment;
    }
 
    public AppointmentDTO getLastAppointmentbyUserId(int userId) {
        logger.info("Fetching last appointment for user ID: {}", userId);
        Appointment appointment = appointmentRepo.getLastAppointmentbyUserId(userId);
        if (appointment == null) {
            logger.error("Appointment not found for user ID: {}", userId);
            throw new AppointmentNotFoundException("Appointment not found for the user with ID: " + userId);
        }
        return AppointmentMapper.mapToAppointmentDTO(appointment);
    }
}
