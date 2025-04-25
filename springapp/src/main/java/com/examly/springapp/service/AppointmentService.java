package com.examly.springapp.service;
 
import java.util.List;
import java.util.Optional;
 
import com.examly.springapp.model.Appointment;
import com.examly.springapp.model.AppointmentDTO;
 
public interface AppointmentService {
 
    public AppointmentDTO addAppointment(AppointmentDTO appointmentDTO);
 
    public List<AppointmentDTO> getAppointmentsbyUserId(int userId);
 
    public List<AppointmentDTO> getAllAppointments();
 
    public AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO, long appointmentId);
 
    public String deleteAppointment(long appointmentId);
 
    public Optional<Appointment> getAppointmentById(long appointmentId);
 
    public AppointmentDTO getLastAppointmentbyUserId(int userId);

}