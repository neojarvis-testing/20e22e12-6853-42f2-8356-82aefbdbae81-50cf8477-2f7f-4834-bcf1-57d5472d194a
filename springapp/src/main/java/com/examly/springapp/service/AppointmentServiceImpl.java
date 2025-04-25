package com.examly.springapp.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.stereotype.Service;
 
import com.examly.springapp.model.Appointment;
import com.examly.springapp.model.AppointmentDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.repository.AppointmentRepo;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.repository.VehicleServiceRepo;
import java.util.stream.Collectors;
import com.examly.springapp.mapper.AppointmentMapper;
 
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
 
    public AppointmentDTO addAppointment(AppointmentDTO appointmentDTO) {
        VehicleMaintenance existingService=vehicleServiceRepo.findById(appointmentDTO.getServiceId()).orElse(null);
        User existingUser=userRepo.findById(appointmentDTO.getUserId()).orElse(null);
        Appointment appointment = AppointmentMapper.mapAppointmentDTOToAppointment(appointmentDTO, existingService, existingUser);
        if(existingService==null || existingUser==null){
            return null;
        }
        appointment.setService(existingService);
        appointment.setUser(existingUser);
        appointment=appointmentRepo.save(appointment);
        return AppointmentMapper.mapToAppointmentDTO(appointment);
    }
 
    public List<AppointmentDTO> getAppointmentsbyUserId(int userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return null; // Exceptions will be added in future
        }
        List<Appointment> appointmentList=appointmentRepo.findByUserId(userId);
        List<AppointmentDTO>  list=  appointmentList.stream().map(appointment->AppointmentMapper.mapToAppointmentDTO(appointment)).toList();
        return list;
    }
 
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepo.findAll().stream().map(appointment->AppointmentMapper.mapToAppointmentDTO(appointment)).toList();
    }
 
    public AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO, long appointmentId) {
        Appointment existingAppointment = appointmentRepo.findById(appointmentId).orElse(null);
        if (existingAppointment == null) {
            return null;  // Exceptions will be added in future
        }
        VehicleMaintenance vehicleMaintenance=vehicleServiceRepo.findById(appointmentDTO.getServiceId()).orElse(null);
        User user=userRepo.findById(appointmentDTO.getUserId()).orElse(null);
        Appointment appointment=AppointmentMapper.mapAppointmentDTOToAppointment(appointmentDTO, vehicleMaintenance, user);
        appointment.setAppointmentId(appointmentId);
        appointment.setLocation(appointmentDTO.getLocation());
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setStatus(appointmentDTO.getStatus());
        userRepo.save(user);
        vehicleServiceRepo.save(vehicleMaintenance);
        appointment=appointmentRepo.save(appointment);
        return AppointmentMapper.mapToAppointmentDTO(appointment);
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
 
    public AppointmentDTO getLastAppointmentbyUserId(int userId) {
        Appointment appointment= appointmentRepo.getLastAppointmentbyUserId(userId);
        return AppointmentMapper.mapToAppointmentDTO(appointment);
    }


}