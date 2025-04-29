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
import com.examly.springapp.exception.AppointmentListEmptyException;
import com.examly.springapp.exception.AppointmentNotFoundException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.exception.VehicleMaintenanceServiceNotFoundException;
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
 
    public AppointmentDTO addAppointments(AppointmentDTO appointmentDTO) {
        VehicleMaintenance existingService=vehicleServiceRepo.findById(appointmentDTO.getId()).orElse(null);
        if(existingService==null){
            throw new VehicleMaintenanceServiceNotFoundException("Vehicle Maintenance Service with ID "+existingService.getId()+" not found");
        }
        else{
            User existingUser=userRepo.findById(appointmentDTO.getUserId()).orElse(null);
            Appointment appointment = AppointmentMapper.mapAppointmentDTOToAppointment(appointmentDTO, existingService, existingUser);
            appointment.setService(existingService);
            appointment.setUser(existingUser);
            appointment=appointmentRepo.save(appointment);
            return AppointmentMapper.mapToAppointmentDTO(appointment);
        }
    }

    public Appointment addAppointment(Appointment appointment) {
        VehicleMaintenance existingService=vehicleServiceRepo.findById(appointment.getService().getId()).orElse(null);
        if(existingService==null){
            throw new VehicleMaintenanceServiceNotFoundException("Vehicle Maintenance Service with ID "+existingService.getId()+" not found");
        }
        else{
            User existingUser=userRepo.findById(appointment.getUser().getId()).orElse(null);
            // existingUser=new User(2,"demouser@gmail.com","1122334455","user123","USER");
            appointment.setUser(existingUser);
            appointment.setService(existingService);
            appointment=appointmentRepo.save(appointment);
            return appointment;
        }
    }
 
    public List<AppointmentDTO> getAppointmentsbyUserId(int userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User Name not found"); // Exceptions will be added in future
        }
        List<Appointment> appointmentList=appointmentRepo.findByUserId(userId);
        List<AppointmentDTO>  list=  appointmentList.stream().map(appointment->AppointmentMapper.mapToAppointmentDTO(appointment)).toList();
        return list;
    }
 
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointmentList=appointmentRepo.findAll();
        if(appointmentList.isEmpty()){
            throw new AppointmentListEmptyException("Appointment List is Empty");
        }
        return appointmentList.stream().map(appointment->AppointmentMapper.mapToAppointmentDTO(appointment)).toList();
    }

    public List<Appointment> getAllAppointment() {
        return appointmentRepo.findAll();
    }
 
    // public AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO, long appointmentId) {
    //     Appointment existingAppointment = appointmentRepo.findById(appointmentId).orElse(null);
    //     if (existingAppointment == null) {
    //         return null;  // Exceptions will be added in future
    //     }
    //     VehicleMaintenance vehicleMaintenance=vehicleServiceRepo.findById(appointmentDTO.getServiceId()).orElse(null);
    //     // User user=userRepo.findById(appointmentDTO.getId()).orElse(null);
    //     User user=userRepo.findById(appointmentDTO.getUserId());
    //     Appointment appointment=AppointmentMapper.mapAppointmentDTOToAppointment(appointmentDTO, vehicleMaintenance, user);
    //     appointment.setId(appointmentId);
    //     appointment.setLocation(appointmentDTO.getLocation());
    //     appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
    //     appointment.setStatus(appointmentDTO.getStatus());
    //     userRepo.save(user);
    //     vehicleServiceRepo.save(vehicleMaintenance);
    //     appointment=appointmentRepo.save(appointment);
    //     return AppointmentMapper.mapToAppointmentDTO(appointment);
    // }

    public Appointment updateAppointment(Appointment appointmentDTO, long appointmentId) {
        Appointment existingAppointment = appointmentRepo.findById(appointmentId).orElse(null);
        if (existingAppointment == null) {
            return null;  // Exceptions will be added in future
        }
        VehicleMaintenance vehicleMaintenance=vehicleServiceRepo.findById(appointmentDTO.getId()).orElse(null);
        // User user=userRepo.findById(appointmentDTO.getId()).orElse(null);
        User user=userRepo.findById(1).orElse(null);
        existingAppointment.setId(appointmentId);
        existingAppointment.setLocation(appointmentDTO.getLocation());
        existingAppointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        existingAppointment.setStatus(appointmentDTO.getStatus());
        userRepo.save(user);
        vehicleServiceRepo.save(vehicleMaintenance);
        existingAppointment=appointmentRepo.save(existingAppointment);
        return existingAppointment;
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
        Optional<Appointment> appointment=appointmentRepo.findById(appointmentId);
        if(appointment.isEmpty()){
            throw new AppointmentNotFoundException("Appointment with ID: "+appointmentId+" not found");
        }
        return appointment;
    }
 
    public AppointmentDTO getLastAppointmentbyUserId(int userId) {
        Appointment appointment= appointmentRepo.getLastAppointmentbyUserId(userId);
        if(appointment==null){
            throw new AppointmentNotFoundException("Appointment not found for the uset with ID : "+userId);
        }
        return AppointmentMapper.mapToAppointmentDTO(appointment);
    }


}